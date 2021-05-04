package com.hugman.uhc.module;

import com.hugman.uhc.config.UHCConfig;
import com.hugman.uhc.module.piece.BlockLootModulePiece;
import com.hugman.uhc.module.piece.BucketBreakModulePiece;
import com.hugman.uhc.module.piece.EntityLootModulePiece;
import com.hugman.uhc.module.piece.ModulePiece;
import com.hugman.uhc.module.piece.ModulePieces;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ModulePieceManager {
	public final List<BlockLootModulePiece> blockLootModulePieces;
	public final List<EntityLootModulePiece> entityLootModulePieces;
	public final List<BucketBreakModulePiece> bucketBreakModulePieces;
	private final List<Module> modules;
	private final List<ModulePiece> modulesPieces;

	public ModulePieceManager(UHCConfig config) {
		this.modules = config.getModulesIds().stream().map(Modules::get).collect(Collectors.toList());
		this.modulesPieces = new ArrayList<>();
		this.modules.forEach(module -> modulesPieces.addAll(module.getPieces()));

		this.blockLootModulePieces = getAllModulesPieces(ModulePieces.BLOCK_LOOT);
		this.entityLootModulePieces = getAllModulesPieces(ModulePieces.ENTITY_LOOT);
		this.bucketBreakModulePieces = getAllModulesPieces(ModulePieces.BUCKET_BREAK);
	}

	public List<Module> getModules() {
		return modules;
	}

	private <V extends ModulePiece> List<V> getAllModulesPieces(Identifier id) {
		return modulesPieces.stream().filter(item -> ModulePieces.getId(item).equals(id))
				.map(piece -> (V) piece)
				.collect(Collectors.toList());
	}
}
