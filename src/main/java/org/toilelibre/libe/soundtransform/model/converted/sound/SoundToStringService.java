package org.toilelibre.libe.soundtransform.model.converted.sound;

class SoundToStringService {

    private final Sound2StringHelper helper;

    public SoundToStringService (final Sound2StringHelper helper1) {
        this.helper = helper1;
    }

    public String convert (final Sound input) {
        return this.helper.process (input);
    }
}
