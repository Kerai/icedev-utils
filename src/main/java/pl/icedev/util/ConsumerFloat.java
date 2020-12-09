package pl.icedev.util;

import java.util.function.Consumer;

public interface ConsumerFloat extends Consumer<Float> {
	public void accept(float v);
	
	public default void accept(Float v) {
		this.accept(v.floatValue());
	}

    default ConsumerFloat andThen(ConsumerFloat after) {
        return (float t) -> { accept(t); after.accept(t); };
    }
}
