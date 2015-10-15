class GCJob {
  static triggers = {
    //simple repeatInterval: 200000 // execute job once in 200 seconds
    cron cronExpression: "0 0 1,5 ? * *"
  }

  def execute() {
    log.debug("LOG>> GCJob: start")
    System.gc()
    System.runFinalization()
    System.gc()
    log.debug("LOG>> GCJob: finish")
  }
}