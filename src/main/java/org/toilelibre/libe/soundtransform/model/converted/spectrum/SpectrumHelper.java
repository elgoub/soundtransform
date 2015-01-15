package org.toilelibre.libe.soundtransform.model.converted.spectrum;


public interface SpectrumHelper<T> {

    int f0 (Spectrum<T> fs, int i);

    int getMaxIndex (Spectrum<T> fscep, int i, int sampleRate);

    int freqFromSampleRate (int freq, int sqr2length, int sampleRate);

    Spectrum<T> hps (Spectrum<T> fs, int factor);

}