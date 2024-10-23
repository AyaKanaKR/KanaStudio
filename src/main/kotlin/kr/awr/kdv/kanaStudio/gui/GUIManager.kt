package kr.awr.kdv.kanaStudio.gui

import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta

class GUIManager(private val mainMenu: MainMenu) : Listener {

    fun onPlayerClick(player: Player) {
        // 플레이어가 클릭할 때 GUI를 연다.
        mainMenu.openInventory(player)
    }

    // GUI 클릭 이벤트 처리
    @EventHandler
    fun onInventoryClick(event: InventoryClickEvent) {
        val player = event.whoClicked as? Player ?: return

        // 해당 인벤토리가 "카나 스튜디오" 인벤토리인지 확인
        if (event.view.title != "카나 스튜디오") return

        // 클릭된 아이템 확인
        val clickedItem: ItemStack? = event.currentItem
        if (clickedItem == null || clickedItem.type == Material.AIR) {
            player.sendMessage("빈 슬롯을 클릭했습니다!")
            return
        }

        // 아이템 이름 가져오기 (ItemMeta를 사용)
        val itemMeta: ItemMeta? = clickedItem.itemMeta
        val itemName = itemMeta?.displayName ?: "이름 없음"

        player.sendMessage("아이템 클릭: $itemName")

        // 클릭된 아이템을 확인하고, 추가적인 로직을 수행
        if (clickedItem.type == Material.DIAMOND) {
            player.sendMessage("다이아몬드를 클릭했습니다!")
        }

        // 새로운 아이템을 인벤토리에 추가하는 예시
        val newItem = ItemStack(Material.EMERALD, 1) // 새로운 아이템: 에메랄드 1개
        val newItemMeta = newItem.itemMeta
        newItemMeta?.setItemName("특별한 에메랄드") // 아이템 이름 설정
        newItemMeta?.removeEnchantments()
        newItem.itemMeta = newItemMeta

        player.inventory.addItem(newItem) // 플레이어 인벤토리에 추가

        // 기본 행동 취소 (아이템을 옮기지 못하도록)
        event.isCancelled = true
    }
}
