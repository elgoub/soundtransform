package org.toilelibre.libe.soundtransform.model.converted.spectrum;

import org.toilelibre.libe.soundtransform.model.converted.sound.Sound;
import org.toilelibre.libe.soundtransform.model.converted.SoundTransformation;
import org.toilelibre.libe.soundtransform.model.observer.LogAware;
import org.toilelibre.libe.soundtransform.model.observer.LogEvent;
import org.toilelibre.libe.soundtransform.model.observer.Observer;

public abstract class AbstractFrequencySoundTransformation implements SoundTransformation, LogAware {

	private Observer []	           observers;

	private FourierTransformHelper	fourierTransformHelper;

	public AbstractFrequencySoundTransformation () {
		this.fourierTransformHelper = new org.toilelibre.libe.soundtransform.infrastructure.service.fourier.CommonsMath3FourierTransformHelper ();
	}

	public abstract Sound initSound (Sound input);

	public abstract Spectrum transformFrequencies (Spectrum fs, int offset, int powOf2NearestLength, int length);

	public abstract int getOffsetFromASimpleLoop (int i, double step);

	public abstract double getLowThreshold (double defaultValue);

	public int getWindowLength (double freqmax) {
		return (int) Math.pow (2, Math.ceil (Math.log (freqmax) / Math.log (2)));
	}

	@Override
	public void setObservers (Observer [] observers1) {
		this.observers = observers1;
	}

	@Override
	public void log (LogEvent logEvent) {
		if (this.observers == null) {
			return;
		}
		for (Observer transformObserver : this.observers) {
			transformObserver.notify (logEvent);
		}
	}

	@Override
	public Sound transform (Sound sound) {
		Sound output = this.fourierTransformHelper.transform (this, sound);
		return output;
	}

}