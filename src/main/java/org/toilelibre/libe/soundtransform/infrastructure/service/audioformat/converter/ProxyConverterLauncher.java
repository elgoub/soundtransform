package org.toilelibre.libe.soundtransform.infrastructure.service.audioformat.converter;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Map.Entry;

import org.toilelibre.libe.soundtransform.infrastructure.service.Processor;
import org.toilelibre.libe.soundtransform.model.exception.SoundTransformException;
import org.toilelibre.libe.soundtransform.model.inputstream.StreamInfo;
import org.toilelibre.libe.soundtransform.model.inputstream.convert.ConverterLauncher;
import org.toilelibre.libe.soundtransform.model.logging.AbstractLogAware;

@Processor
class ProxyConverterLauncher extends AbstractLogAware<ProxyConverterLauncher> implements ConverterLauncher<Converter> {

    @Override
    public Entry<StreamInfo, ByteArrayOutputStream> convert (final Converter converter, final InputStream inputStream) throws SoundTransformException {
        return converter.convert (inputStream);
    }

}
