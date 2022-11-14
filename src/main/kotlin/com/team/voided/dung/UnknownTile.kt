package com.team.voided.dung

class UnknownTile(var possible: Array<Tile> ) {

    companion object {
        val EMPTY = UnknownTile(emptyArray())
        val FULL = UnknownTile(arrayOf(Tile.NOTHING, Tile.IRON, Tile.NETHER, Tile.DEEP, Tile.COPPER))
    }

    private var collapsed: Boolean = false

    fun isCollapsed(): Boolean {
        return this.collapsed
    }

    fun collapse() {
        this.collapsed = true;
    }


}