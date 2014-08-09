package org.toilelibre.soundtransform.transforms;

import org.toilelibre.soundtransform.objects.Sound;

public class NormalizeSoundTransformation implements SoundTransformation {
	
	public NormalizeSoundTransformation () {
    }


	@Override
	public Sound transform (Sound input) {
		return NormalizeSoundTransformation.normalize (input);
	}

	private static Sound normalize (Sound sound) {
		double [] data = sound.getSamples ();
		double [] newdata = new double [sound.getSamples ().length];
		// this is the raw audio data -- no header

		// find the max:
		double max = 0;
		for (int i = 0; i < data.length; i++) {
			if (Math.abs (data [i]) > max)
				max = Math.abs (data [i]);
		}

		// now find the result, with scaling:
		double maxValue = Math.pow (256, sound.getNbBytesPerFrame () - 1) / 2;
		for (int i = 0; i < data.length; i++) {
			double underOne = data [i] / max;
			double rescaled = underOne * maxValue;
			newdata [i] = Math.floor (rescaled);
		}

		// normalized result in newdata
		return new Sound (newdata, sound.getNbBytesPerFrame (), sound.getFreq());
	}
}