package org.toilelibre.libe.soundtransform.infrastructure.service.record.exporter;

import java.io.ByteArrayOutputStream;

import org.toilelibre.libe.soundtransform.infrastructure.service.Processor;
import org.toilelibre.libe.soundtransform.model.record.exporter.BytesExporterFromThread;
import org.toilelibre.libe.soundtransform.model.record.exporter.OutputAsByteArrayOutputStream;

@Processor
class ByteArrayOutputStreamExporter implements BytesExporterFromThread<ByteArrayOutputStream>, OutputAsByteArrayOutputStream {

    private ByteArrayOutputStream outputStream;
    private int bufferSize;

    ByteArrayOutputStreamExporter () {
    }

    @Override
    public void init (final int bufferSize1) {
        this.bufferSize = bufferSize1;
        this.outputStream = new ByteArrayOutputStream (bufferSize);
    }

    @Override
    public int getReportedBufferSize () {
        return this.bufferSize;
    }


    @Override
    public void export (final byte [] byteArray, final int readSize) {
        this.outputStream.write (byteArray, 0, readSize);
    }

    @Override
    public ByteArrayOutputStream getOutput () {
        return this.outputStream;
    }

}
