package pl.icedev.util;

import java.util.function.Consumer;

public interface ConsumerInt extends Consumer<Integer> {
	public void accept(int v);
	
	public default void accept(Integer v) {
		this.accept(v.intValue());
	}

    default ConsumerInt andThen(ConsumerInt after) {
        return (int t) -> { accept(t); after.accept(t); };
    }
}
