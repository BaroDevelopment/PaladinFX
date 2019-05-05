import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage


class App : Application() {

    @Throws(Exception::class)
    override fun start(primaryStage: Stage?) {
        val root = FXMLLoader.load<Parent>(javaClass.getResource("/fxml/sample.fxml"))
        val scene = Scene(root, 600.0, 400.0)
        primaryStage?.setTitle("Paladin GUI")
        primaryStage?.setScene(scene)
        primaryStage?.show()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            launch(App::class.java, *args)
        }
    }
}