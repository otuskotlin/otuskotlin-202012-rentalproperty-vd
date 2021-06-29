package ru.otus.otuskotlin.vd.rentalproperty.kmp.pipelines

class Operation<T>
private constructor(
  private val checkPrecondition: Predicate<T>,
  private val runOperation: Runnable<T>,
  private val handleError: ErrorHandler<T>,
  private val checkValues: List<String>,
) : IOperation<T> {
  override suspend fun execute(context: T) {
    try {
      if (checkPrecondition(context)) runOperation(context)
    } catch (throwable: Throwable) {
      handleError(context, throwable)
    }
  }

  @PipelineDsl
  class Builder<T> : IOperationBuilder<T> {
    private var checkPrecondition: Predicate<T> = { true }
    private var runOperation: Runnable<T> = {}
    private var handleError: ErrorHandler<T> = { throw it }
    private var checkValues: List<String> = emptyList()

    fun startIf(block: Predicate<T>) {
      checkPrecondition = block
    }

    fun execute(block: Runnable<T>) {
      runOperation = block
    }

    fun onError(block: ErrorHandler<T>) {
      handleError = block
    }

    fun setCheckValues(block: List<String>) {
      checkValues = block
    }

    fun getCheckValues() = checkValues

    override fun build(): Operation<T> =
      Operation(checkPrecondition, runOperation, handleError, checkValues)
  }
}