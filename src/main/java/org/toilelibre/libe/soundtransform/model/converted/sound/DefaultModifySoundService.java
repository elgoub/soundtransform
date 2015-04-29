package org.toilelibre.libe.soundtransform.model.converted.sound;

import org.toilelibre.libe.soundtransform.model.converted.FormatInfo;
import org.toilelibre.libe.soundtransform.model.exception.SoundTransformException;

final class DefaultModifySoundService implements ModifySoundService {

    private final SoundAppender soundAppender;

    public DefaultModifySoundService (final SoundAppender soundAppender1) {
        this.soundAppender = soundAppender1;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * org.toilelibre.libe.soundtransform.model.converted.sound.ModifySoundService
     * #append(org.toilelibre.libe.soundtransform.model.converted.sound.Sound[],
     * org.toilelibre.libe.soundtransform.model.converted.sound.Sound[])
     */
    @Override
    public Channel [] append (final Channel [] sounds1, final Channel [] sounds2) throws SoundTransformException {
        if (sounds1.length != sounds2.length) {
            throw new SoundTransformException (ModifySoundServiceErrorCode.DIFFERENT_NUMBER_OF_CHANNELS, new IllegalArgumentException (), sounds1.length, sounds2.length);
        }
        final Channel [] result = new Channel [sounds1.length];

        for (int i = 0 ; i < sounds1.length ; i++) {
            result [i] = this.soundAppender.append (sounds1 [i], sounds2 [i]);
        }
        return result;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * org.toilelibre.libe.soundtransform.model.converted.sound.ModifySoundService
     * #
     * changeFormat(org.toilelibre.libe.soundtransform.model.converted.sound.Sound
     * [], org.toilelibre.libe.soundtransform.model.converted.FormatInfo)
     */
    @Override
    public Channel [] changeFormat (final Channel [] input, final FormatInfo formatInfo) {
        return this.changeFormat (input, formatInfo.getSampleSize (), (int) formatInfo.getSampleRate ());
    }

    private Channel [] changeFormat (final Channel [] input, final int sampleSize, final int sampleRate) {
        final Channel [] result = new Channel [input.length];
        for (int i = 0 ; i < input.length ; i++) {
            result [i] = input [i];
            result [i] = this.soundAppender.changeNbBytesPerSample (result [i], sampleSize);
            result [i] = this.soundAppender.resizeToSampleRate (result [i], sampleRate);
        }
        return result;
    }

}
