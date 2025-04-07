package at.dumphey.itemeditor.itemeditor.modules.enchantments;

import at.dumphey.itemeditor.itemeditor.ItemEditor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class EnchantUtils {

    private static final Map<Enchantment, Material> fancyMaterials = new HashMap<>();

    static {
        addFancyMaterial(Material.BOW, Enchantment.ARROW_DAMAGE, Enchantment.ARROW_FIRE, Enchantment.ARROW_INFINITE,
                Enchantment.ARROW_KNOCKBACK);
        addFancyMaterial(Material.TRIDENT, Enchantment.CHANNELING, Enchantment.RIPTIDE, Enchantment.LOYALTY,
                Enchantment.IMPALING);
        addFancyMaterial(Material.GOLDEN_SWORD, Enchantment.DAMAGE_ALL, Enchantment.DAMAGE_ARTHROPODS,
                Enchantment.DAMAGE_UNDEAD, Enchantment.FIRE_ASPECT, Enchantment.KNOCKBACK, Enchantment.LOOT_BONUS_MOBS,
                Enchantment.SWEEPING_EDGE);
        addFancyMaterial(Material.DIAMOND_PICKAXE, Enchantment.LOOT_BONUS_BLOCKS, Enchantment.DIG_SPEED,
                Enchantment.SILK_TOUCH);
        addFancyMaterial(Material.IRON_BOOTS, Enchantment.PROTECTION_FALL, Enchantment.FROST_WALKER,
                Enchantment.DEPTH_STRIDER);
        addFancyMaterial(Material.LEATHER_CHESTPLATE, Enchantment.PROTECTION_ENVIRONMENTAL,
                Enchantment.PROTECTION_EXPLOSIONS, Enchantment.PROTECTION_FIRE, Enchantment.PROTECTION_PROJECTILE,
                Enchantment.THORNS);
        addFancyMaterial(Material.TURTLE_HELMET, Enchantment.WATER_WORKER, Enchantment.OXYGEN);
        addFancyMaterial(Material.OBSIDIAN, Enchantment.DURABILITY);
        addFancyMaterial(Material.WITHER_SKELETON_SKULL, Enchantment.BINDING_CURSE, Enchantment.VANISHING_CURSE);
        addFancyMaterial(Material.EXPERIENCE_BOTTLE, Enchantment.MENDING);
        addFancyMaterial(Material.FISHING_ROD, Enchantment.LURE, Enchantment.LUCK);

        if (ItemEditor.INSTANCE.getCompatUtils().getMajorVersion() >= 14) {
            safeAddFancyMaterial(Material.getMaterial("CROSSBOW"), "MULTISHOT", "QUICK_CHARGE", "PIERCING");
        }

        if (ItemEditor.INSTANCE.getCompatUtils().getMajorVersion() >= 16) {
            safeAddFancyMaterial(Material.IRON_BOOTS, "SOUL_SPEED");
        }
    }

    private static Enchantment getByEnumName(String enumName) {
        return Arrays.stream(Enchantment.values())
                .filter(p -> ItemEditor.INSTANCE.getCompatUtils().getEnchantmentKey(p).equalsIgnoreCase(enumName))
                .findFirst()
                .orElse(null);
    }

    private static void safeAddFancyMaterial(Material material, String... enchantmentKeys) {
        for (String key : enchantmentKeys) {
            Enchantment enchantment = getByEnumName(key);
            if (enchantment != null) {
                fancyMaterials.put(enchantment, material);
            } else {
                System.out.println("[ItemEditor] Enchantment not found: " + key);
            }
        }
    }

    private static void addFancyMaterial(Material material, Enchantment... enchantments) {
        for (Enchantment enchantment : enchantments) {
            if (enchantment != null) {
                fancyMaterials.put(enchantment, material);
            }
        }
    }

    public static Material getMaterial(Enchantment enchantment) {
        if (fancyMaterials.containsKey(enchantment)) {
            return fancyMaterials.get(enchantment);
        }
        return Material.ENCHANTED_BOOK;
    }
}
