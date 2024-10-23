package kr.awr.kdv.kanaStudio.gui


import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory
class MainMenu  {


    fun openInventory(player: Player?) {
        // 예시로 간단한 인벤토리를 연다.
        val inventory: Inventory = player!!.server.createInventory(null, 9, "카나 스튜디오")
        player.openInventory(inventory)
    }



}