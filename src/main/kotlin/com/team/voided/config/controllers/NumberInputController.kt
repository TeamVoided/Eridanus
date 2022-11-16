package com.team.voided.config.controllers

import dev.isxander.yacl.api.Option
import dev.isxander.yacl.api.utils.Dimension
import dev.isxander.yacl.gui.AbstractWidget
import dev.isxander.yacl.gui.YACLScreen
import dev.isxander.yacl.gui.controllers.string.IStringController
import dev.isxander.yacl.gui.controllers.string.StringControllerElement
import java.math.BigDecimal

class NumberInputController constructor(option: Option<Number>): IStringController<Number> {
    private val option: Option<Number>

    init {
        this.option = option
    }

    override fun option(): Option<Number> = option

    override fun getString(): String = option.pendingValue().toString()

    override fun setFromString(string: String?) = option.requestSet(BigDecimal(string))

    override fun provideWidget(screen: YACLScreen?, widgetDimension: Dimension<Int>?): AbstractWidget = StringControllerElement(this, screen, widgetDimension)
}
