package pages.proposals.view

import models.ProposalModel
import react.RProps

interface PageProposalViewProps : RProps {
    @JsName("proposal")
    var proposal: ProposalModel
}
