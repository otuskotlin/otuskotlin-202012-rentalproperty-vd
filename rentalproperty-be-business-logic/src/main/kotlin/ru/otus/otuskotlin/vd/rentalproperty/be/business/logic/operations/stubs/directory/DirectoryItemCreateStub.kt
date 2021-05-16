package ru.otus.otuskotlin.vd.rentalproperty.be.business.logic.operations.stubs.directory

import ru.otus.otuskotlin.vd.rentalproperty.be.common.context.BeContext
import ru.otus.otuskotlin.vd.rentalproperty.be.common.context.BeContextStatus
import ru.otus.otuskotlin.vd.rentalproperty.be.common.models.StubCase
import ru.otus.otuskotlin.vd.rentalproperty.be.directory.model.AppliancesModel
import ru.otus.otuskotlin.vd.rentalproperty.be.directory.model.BathroomTypeModel
import ru.otus.otuskotlin.vd.rentalproperty.be.directory.model.ConvenienceModel
import ru.otus.otuskotlin.vd.rentalproperty.be.directory.model.DirectoryItemModel
import ru.otus.otuskotlin.vd.rentalproperty.kmp.pipelines.IOperation
import ru.otus.otuskotlin.vd.rentalproperty.kmp.pipelines.operation
import ru.otus.otuskotlin.vd.rentalproperty.kmp.pipelines.pipeline

object DirectoryItemCreateStub : IOperation<BeContext> by pipeline({
  startIf {
    status == BeContextStatus.RUNNING
        && stubCase != StubCase.NONE
  }

  operation {
    startIf { stubCase == StubCase.DIRECTORY_CREATE_SUCCESS }
    execute {
      responseDirectoryItem = DirectoryItemModel.STUB
      status = BeContextStatus.FINISHING
    }
  }
  operation {
    startIf { stubCase == StubCase.DIRECTORY_APPLIANCES_SUCCESS }
    execute {
      responseDirectoryItem = AppliancesModel.STUB_AIR_CONDITIONER
      status = BeContextStatus.FINISHING
    }
  }
  operation {
    startIf { stubCase == StubCase.DIRECTORY_BATHROOMTYPE_SUCCESS }
    execute {
      responseDirectoryItem = BathroomTypeModel.STUB_COMBINED
      status = BeContextStatus.FINISHING
    }
  }
  operation {
    startIf { stubCase == StubCase.DIRECTORY_CONVENIENCES_SUCCESS }
    execute {
      responseDirectoryItem = ConvenienceModel.STUB_FURNITURE_IN_KITCHEN
      status = BeContextStatus.FINISHING
    }
  }
})