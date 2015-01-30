package org.toilelibre.libe.soundtransform.actions.fluent;

import java.io.File;
import java.io.InputStream;

import org.toilelibre.libe.soundtransform.model.converted.sound.Sound;
import org.toilelibre.libe.soundtransform.model.exception.SoundTransformException;
import org.toilelibre.libe.soundtransform.model.inputstream.InputStreamInfo;

public interface FluentClientReady {
    FluentClientWithInputStream withAudioInputStream (InputStream is) throws SoundTransformException;

    FluentClientWithFile withClasspathResource (String resource) throws SoundTransformException;

    FluentClientWithFile withFile (File file) throws SoundTransformException;

    FluentClientWithInputStream withRawInputStream (InputStream is, InputStreamInfo isInfo) throws SoundTransformException;

    FluentClientSoundImported withSounds (Sound [] sounds);

    FluentClientWithFreqs withFreqs (int [] freqs) throws SoundTransformException;
}
