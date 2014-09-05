package org.toilelibre.libe.soundtransform.transforms;

import org.toilelibre.libe.soundtransform.objects.FrequenciesState;
import org.toilelibre.libe.soundtransform.objects.Sound;

public class NoOpFrequencySoundTransformation extends AbstractFrequencySoundTransformation {

	public NoOpFrequencySoundTransformation () {
	}

	@Override
	public FrequenciesState transformFrequencies (FrequenciesState fs, int offset, int powOf2NearestLength, int length) {
		return fs;
	}

	@Override
	public Sound initSound (Sound input) {
		long [] newdata = new long [input.getSamples ().length];
		return new Sound (newdata, input.getNbBytesPerSample (), input.getFreq (), input.getChannelNum ());
	}

	@Override
	protected int getOffsetFromASimpleLoop (int i, double step) {
		return 0;
	}

	@Override
	protected double getLowThreshold (double defaultValue) {
		return defaultValue;
	}
}