package org.team.voided.eridanus.config.controllers

import dev.isxander.yacl.api.Option
import dev.isxander.yacl.api.utils.Dimension
import dev.isxander.yacl.gui.AbstractWidget
import dev.isxander.yacl.gui.YACLScreen
import dev.isxander.yacl.gui.controllers.string.IStringController
import dev.isxander.yacl.gui.controllers.string.StringControllerElement

class CharInputController constructor(option: Option<Char>): IStringController<Char> {
    private val option: Option<Char>

    init {
        this.option = option
    }

    override fun option(): Option<Char> = option

    override fun getString(): String = option.pendingValue().toString()

    override fun setFromString(string: String?) = option.requestSet(string?.get(0) ?: '_')

    override fun provideWidget(screen: YACLScreen?, widgetDimension: Dimension<Int>?): AbstractWidget = StringControllerElement(this, screen, widgetDimension)
}
