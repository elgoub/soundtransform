package org.toilelibre.libe.soundtransform;

import java.io.File;
import java.io.InputStream;

import org.junit.Assert;
import org.junit.Test;
import org.toilelibre.libe.soundtransform.infrastructure.service.converted.sound.transforms.SlowdownSoundTransform;
import org.toilelibre.libe.soundtransform.infrastructure.service.converted.sound.transforms.SlowdownSoundTransform.SlowdownSoundTransformErrorCode;
import org.toilelibre.libe.soundtransform.infrastructure.service.observer.Slf4jObserver;
import org.toilelibre.libe.soundtransform.ioc.ApplicationInjector.$;
import org.toilelibre.libe.soundtransform.ioc.SoundTransformTest;
import org.toilelibre.libe.soundtransform.model.converted.sound.CallTransformService;
import org.toilelibre.libe.soundtransform.model.converted.sound.Sound;
import org.toilelibre.libe.soundtransform.model.exception.SoundTransformException;
import org.toilelibre.libe.soundtransform.model.inputstream.AudioFileService;
import org.toilelibre.libe.soundtransform.model.inputstream.StreamInfo;
import org.toilelibre.libe.soundtransform.model.inputstream.fromsound.SoundToInputStreamService;
import org.toilelibre.libe.soundtransform.model.inputstream.readsound.InputStreamToSoundService;
import org.toilelibre.libe.soundtransform.model.logging.LogEvent.LogLevel;

public class SlowdownSoundTest extends SoundTransformTest {

    @SuppressWarnings ("unchecked")
    @Test
    public void testSlowdown () throws SoundTransformException {
        final ClassLoader classLoader = Thread.currentThread ().getContextClassLoader ();
        final File input = new File (classLoader.getResource ("before.wav").getFile ());
        final File output = new File (new File (classLoader.getResource ("before.wav").getFile ()).getParent () + "/after.wav");

        final InputStream is = $.select (AudioFileService.class).streamFromFile (input);
        Sound sound = ((InputStreamToSoundService<InputStreamToSoundService<?>>) $.select (InputStreamToSoundService.class)).setObservers (new Slf4jObserver (LogLevel.WARN)).fromInputStream (is);
        sound = new Sound ( ((CallTransformService<CallTransformService<?>>) $.select (CallTransformService.class)).setObservers (new Slf4jObserver (LogLevel.WARN)).apply (sound.getChannels (), new SlowdownSoundTransform (1024, 2.5f, 2048)));
        ((SoundToInputStreamService<SoundToInputStreamService<?>>) $.select (SoundToInputStreamService.class)).setObservers (new Slf4jObserver (LogLevel.WARN)).toStream (sound, StreamInfo.from (sound.getFormatInfo (), sound));
        $.select (AudioFileService.class).fileFromStream (is, output);
    }

    @Test (expected = SoundTransformException.class)
    public void testSlowdownNotPowerOf2 () throws SoundTransformException {
        try {
            new SlowdownSoundTransform (1024, 2.5f, 2049);
            Assert.fail ();
        } catch (final SoundTransformException ste) {
            Assert.assertEquals (ste.getErrorCode (), SlowdownSoundTransformErrorCode.WINDOW_LENGTH_IS_NOT_A_POWER_OF_2);
            throw ste;
        }
    }

    @Test (expected = SoundTransformException.class)
    public void testSlowdownNotTwiceLessThanWindowLength () throws SoundTransformException {
        try {
            new SlowdownSoundTransform (1025, 2.5f, 2048);
            Assert.fail ();
        } catch (final SoundTransformException ste) {
            Assert.assertEquals (ste.getErrorCode (), SlowdownSoundTransformErrorCode.WINDOW_LENGTH_IS_LOWER_THAN_TWICE_THE_STEP);
            throw ste;
        }
    }
}
