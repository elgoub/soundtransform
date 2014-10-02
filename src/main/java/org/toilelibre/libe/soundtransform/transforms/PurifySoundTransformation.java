package org.toilelibre.libe.soundtransform.transforms;

import org.apache.commons.math3.complex.Complex;
import org.toilelibre.libe.soundtransform.objects.FrequenciesState;

public class PurifySoundTransformation extends NoOpFrequencySoundTransformation {


	public PurifySoundTransformation () {
	}

	@Override
	public FrequenciesState transformFrequencies (FrequenciesState fs, int offset, int powOf2NearestLength, int length) {
		Complex [] newAmpl = new Complex [powOf2NearestLength];
		int max = 0;
		double maxValue = 0;
		for (int j = 0; j < length; j++) {
			double tmp = Math.sqrt (Math.pow (fs.getState () [j].getReal (), 2) + Math.pow (fs.getState () [j].getImaginary (), 2));
			if (tmp > maxValue && j > 100 && j < fs.getMaxfrequency () / 2) {
				max = j;
				maxValue = tmp;
			}
		}
		for (int j = 0; j < powOf2NearestLength; j++) {
			newAmpl [j] = fs.getState () [j].multiply (Math.exp (- (Math.pow (j - max, 2)) / 100));
		}
		return new FrequenciesState (newAmpl, fs.getMaxfrequency ());
	}

	@Override
	protected double getLowThreshold (double defaultValue) {
		return 100;
	}

	@Override
	protected int getWindowLength (double freqmax) {
		return super.getWindowLength (freqmax);
	}

}
