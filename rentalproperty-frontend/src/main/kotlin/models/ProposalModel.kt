package models

data class ProposalModel(
  override var id: ProposalIdModel = ProposalIdModel.NONE,
  override val avatar: String = "",
  override val title: String = "",
  override val description: String = "",
  override val linkView: String = "",
  override val linkEdit: String = "",
  override val linkDelete: String = "",
  override val tags: MutableSet<TagModel> = mutableSetOf(),
  override val techDets: MutableSet<TechDetModel> = mutableSetOf(),
) : IMarketplaceItem {
}
