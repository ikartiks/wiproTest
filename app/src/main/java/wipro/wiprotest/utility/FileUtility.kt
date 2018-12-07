package wipro.wiprotest.utility

import android.content.Context
import wipro.wiprotest.model.Data
import java.io.FileNotFoundException
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.lang.Exception

fun saveObject(context:Context, data :Data,fileName:String){

    val file =  context.openFileOutput(fileName, Context.MODE_PRIVATE);
    val out = ObjectOutputStream(file)

    // Method for serialization of object
    out.writeObject(data)

    out.close()
    file.close()
}

fun retriveObject(context:Context,fileName:String):Data?{

    try {
        val fis = context.openFileInput(fileName)
        val ois = ObjectInputStream(fis)
        val obj = ois.readObject()
        ois.close()
        fis.close()
        return obj as Data
    }catch (e:Exception){
        return null
    }

}

