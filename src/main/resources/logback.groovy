import ch.qos.logback.classic.AsyncAppender
import ch.qos.logback.classic.encoder.PatternLayoutEncoder
import ch.qos.logback.core.ConsoleAppender
import ch.qos.logback.core.FileAppender
import ch.qos.logback.core.rolling.RollingFileAppender
import ch.qos.logback.core.rolling.TimeBasedRollingPolicy

import static ch.qos.logback.classic.Level.INFO

scan("30 seconds")
def LOG_PATH = "C:/Users/pc/Desktop/LogbackTest"
def LOG_ARCHIVE = "${LOG_PATH}/archive"
appender("Console-Appender", ConsoleAppender) {
    encoder(PatternLayoutEncoder) {
        pattern = "%msg%n"
    }
}
appender("File-Appender", FileAppender) {
    file = "${LOG_PATH}/logfile.log"
    encoder(PatternLayoutEncoder) {
        pattern = "%msg%n"
        outputPatternAsHeader = true
    }
}

appender("File-Appender2", FileAppender) {
    file = "${LOG_PATH}/logfile2.log"
    encoder(PatternLayoutEncoder) {
        pattern = "%msg%n"
        outputPatternAsHeader = true
    }
}

appender("RollingFile-Appender", RollingFileAppender) {
    file = "${LOG_PATH}/rollingfile.log"
    rollingPolicy(TimeBasedRollingPolicy) {
        fileNamePattern = "${LOG_ARCHIVE}/rollingfile.log%d{yyyy-MM-dd}.log"
        maxHistory = 30
        totalSizeCap = "1KB"
    }
    encoder(PatternLayoutEncoder) {
        pattern = "%msg%n"
    }
}

appender("Async-Appender", AsyncAppender) {
    appenderRef("RollingFile-Appender")
}

logger("com.scarlatti", INFO, ["Console-Appender", "File-Appender", "Async-Appender"], false)
logger("*", INFO, ["File-Appender2"], false)
root(INFO, ["Console-Appender"])