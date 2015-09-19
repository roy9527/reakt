package com.github.andrewoma.react

public interface ReadWriteProperty<in R, T> {
    public fun get(thisRef: R, desc: PropertyMetadata): T
    public fun set(thisRef: R, desc: PropertyMetadata, value: T)
}

/**
 * Property is a delegated property that stores properties in a plain JavaScript object.
 * React doesn't find properties declared via a prototype, so this works around that limitation.
 */
public class Property<in R, T> : ReadWriteProperty<R, T> {
    override fun get(thisRef: R, desc: PropertyMetadata): T {
        return getProperty(thisRef as Any, desc.name)
    }

    override fun set(thisRef: R, desc: PropertyMetadata, value: T) {
        setProperty(thisRef as Any, desc.name, value)
    }
}

@native("Reakt.getProperty") @Suppress("UNUSED_PARAMETER")
private fun <T> getProperty(thisRef: Any, name: String): T = noImpl

@native("Reakt.setProperty") @Suppress("UNUSED_PARAMETER")
private fun <T> setProperty(thisRef: Any, name: String, value: T): Unit = noImpl

@native("Reakt.getProperties") @Suppress("UNUSED_PARAMETER")
private fun <T> getProperties(thisRef: Any): T = noImpl