package org.toilelibre.libe.soundtransform.actions.fluent;

import java.io.File;

import org.toilelibre.libe.soundtransform.model.exception.SoundTransformException;

public interface BuildableFluentClientOperationWithFile extends FluentClientWithFile, BuildableFluentClientOperation {

    /**
     * Shortcut for importToStream ().importToSound () : Conversion from a File
     * to a Sound
     *
     * @return the client, with a sound imported
     * @throws SoundTransformException
     *             if one of the two import fails
     */
    @Override
    BuildableFluentClientOperationSoundImported convertIntoSound () throws SoundTransformException;

    /**
     * Opens the current file and converts it into an InputStream, ready to be
     * read (or to be written to a file)
     *
     * @return the client, with an inputStream
     * @throws SoundTransformException
     *             the current file is not valid, or the conversion did not work
     */
    @Override
    BuildableFluentClientOperationWithInputStream importToStream () throws SoundTransformException;

    /**
     * Plays the current audio data, converting it temporarily into a sound
     *
     * @return the client, with a file
     * @throws SoundTransformException
     *             could not play the current audio data
     */
    @Override
    BuildableFluentClientOperationWithFile playIt () throws SoundTransformException;

    /**
     * Stops the client pipeline and returns the obtained file
     *
     * @return a file
     */
    @Override
    File stopWithFile ();

}