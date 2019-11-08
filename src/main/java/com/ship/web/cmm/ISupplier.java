package com.ship.web.cmm;

@FunctionalInterface
public interface ISupplier<T> {
	public T get();
}
