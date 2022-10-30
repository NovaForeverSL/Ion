package net.horizonsend.ion.common.database.collections

import net.horizonsend.ion.common.database.Collection
import net.horizonsend.ion.common.database.Document
import net.horizonsend.ion.common.database.enums.Achievement
import org.bson.codecs.pojo.annotations.BsonId
import org.litote.kmongo.eq
import org.litote.kmongo.findOne
import java.util.UUID
import net.horizonsend.ion.common.database.enums.Ranktrack
import net.horizonsend.ion.common.database.enums.RanktrackTraits

class PlayerData private constructor(
	@BsonId val minecraftUUID: UUID,
	var discordId: Long? = null,
	var minecraftUsername: String? = null,
	var achievements: MutableList<Achievement> = mutableListOf(),
	var voteTimes: MutableMap<String, Long> = mutableMapOf(),
	var lastLoggofftime: MutableMap<String, Long> = mutableMapOf(),
	var ranktracktype: Ranktrack = Ranktrack.REFUGEE,
	val secondaryRanktrackType: Ranktrack = Ranktrack.REFUGEE,
	var traits: MutableList<RanktrackTraits.Traits> = mutableListOf(),
	var xp: Int = 0
) : Document() {
	companion object : Collection<PlayerData>(PlayerData::class) {
		override fun construct(id: UUID): PlayerData = PlayerData(id)

		operator fun get(minecraftUsername: String): PlayerData? {
			return collection.findOne(PlayerData::minecraftUsername eq minecraftUsername)
		}

		operator fun get(discordId: Long): PlayerData? {
			return collection.findOne(PlayerData::discordId eq discordId)
		}
	}

	override fun update() = update(this)
}