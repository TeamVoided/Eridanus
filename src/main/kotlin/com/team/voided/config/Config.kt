package com.team.voided.config

import net.minecraft.util.Identifier

abstract class Config<E: Config<E>> constructor(val id: Identifier) {
    protected val values: MutableMap<String, ConfigValue> = LinkedHashMap()

    abstract fun setValue(id: String, value: ConfigValue): E
    abstract fun removeValue(id: String): E
    abstract fun save(): E
    abstract fun load(): E
}
