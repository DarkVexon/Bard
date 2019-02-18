package com.evacipated.cardcrawl.mod.bard.helpers;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class AssetLoader
{
    private AssetManager assets = new AssetManager();

    public Texture loadImage(String fileName)
    {
        if (!assets.isLoaded(fileName, Texture.class)) {
            TextureLoader.TextureParameter param = new TextureLoader.TextureParameter();
            param.minFilter = Texture.TextureFilter.Linear;
            param.magFilter = Texture.TextureFilter.Linear;
            assets.load(fileName, Texture.class, param);
            assets.finishLoadingAsset(fileName);
        }
        return assets.get(fileName, Texture.class);
    }

    public TextureAtlas loadAtlas(String fileName)
    {
        if (!assets.isLoaded(fileName, TextureAtlas.class)) {
            assets.load(fileName, TextureAtlas.class);
            assets.finishLoadingAsset(fileName);
        }
        return assets.get(fileName, TextureAtlas.class);
    }
}