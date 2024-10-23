package kr.awr.kdv.kanaStudio

import kr.awr.kdv.kanaStudio.annotation.ChatFormatter
import kr.awr.kdv.kanaStudio.chat.DefaultChatFormatter
import kr.awr.kdv.kanaStudio.command.GUICommand
import kr.awr.kdv.kanaStudio.gui.GUIManager
import kr.awr.kdv.kanaStudio.gui.MainMenu
import org.bukkit.plugin.java.JavaPlugin

class Main : JavaPlugin() {

    val chatFormatter: ChatFormatter = DefaultChatFormatter()
    private lateinit var guiManager: GUIManager
    private lateinit var guiCommand: GUICommand

    override fun onEnable() {
        val mainMenu = MainMenu()
        guiManager = GUIManager(mainMenu)
        guiCommand = GUICommand(guiManager) // GUIManager를 주입하여 초기화
        server.pluginManager.registerEvents(guiManager, this)
        getCommand("menu")?.setExecutor(guiCommand)
        logger.info("Kana Studio Plugin Enabled!")
    }

    override fun onDisable() {
        logger.info("Kana Studio Plugin Disabled!")
    }
}
