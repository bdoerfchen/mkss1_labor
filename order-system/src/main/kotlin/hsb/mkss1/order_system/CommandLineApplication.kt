package hsb.mkss1.order_system

import hsb.mkss1.order_system.adapters.cli.CLI
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CommandLineApplication(val cli : CLI) : CommandLineRunner {


    override fun run(vararg args: String) {
        cli.open()
    }
}

fun main(args: Array<String>) {
	runApplication<CommandLineApplication>(*args)
}
