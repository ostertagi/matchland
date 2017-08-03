package mmorihiro.larger_circle.controller

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.InputMultiplexer
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import ktx.assets.Assets
import ktx.assets.asset
import ktx.assets.load
import ktx.assets.unload
import ktx.scene2d.Scene2DSkin
import mmorihiro.larger_circle.view.View


class MainListener : ApplicationAdapter() {
    private var currentViews: List<View> = listOf()

    override fun create() {
        loadAssets()
        Scene2DSkin.defaultSkin = asset<Skin>("ui/uiskin.json")
        val puzzleView = PuzzleController().view
        InputMultiplexer().run {
            addProcessor(puzzleView)
            Gdx.input.inputProcessor = this
        }
        currentViews = listOf(puzzleView)
    }

    private fun loadAssets() {
        Assets.manager = AssetManager()
        load<Texture>("background.png")
        load<Texture>("items.png")
        load<Texture>("tile.png")
        load<Texture>("pointer.png")
        load<Texture>("star.png")
        load<Texture>("starBar.png")
        load<Texture>("lifeBar.png")
        load<Skin>("ui/uiskin.json")
        Assets.manager.finishLoading()
    }

    override fun render() {
        Gdx.gl.glClearColor(0 / 255f, 136 / 255f, 170 / 255f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        currentViews.forEach { it.act(Gdx.graphics.deltaTime) }
        currentViews.forEach(Stage::draw)
    }

    override fun resize(width: Int, height: Int) {
        currentViews.forEach { it.vp.update(width, height) }
    }

    override fun dispose() {
        unload("background.png")
        unload("items.png")
        unload("tile.png")
        unload("pointer.png")
        unload("star.png")
        unload("starBar.png")
        unload("lifeBar.png")
        unload("ui/uiskin.json")
        unload("recordBack.png")
        unload("window.png")
        unload("starFrame.png")
        unload("recordStar.png")
    }
}
