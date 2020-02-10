package com.example.myasynctask

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.app.ProgressDialog
import android.app.SearchManager
import android.content.Context
import android.content.DialogInterface
import android.os.AsyncTask
import android.support.annotation.IntegerRes
import android.util.Log
import android.widget.ProgressBar
import kotlin.properties.Delegates

public class MyAsyncTask: AsyncTask<String, Int, Long>, SearchManager.OnCancelListener, DialogInterface.OnCancelListener {

    final var TAG: String = "MyAsyncTask"
    var dialog: ProgressDialog by Delegates.notNull<ProgressDialog>()
    var context: Context by Delegates.notNull<Context>()

    constructor(context: Context){
        this.context = context
    }

    override protected fun onPreExecute() {
        Log.d(TAG, "onPreExecute")
        dialog = ProgressDialog(context)
        dialog.setTitle("Please wait")
        dialog.setMessage("Loading data...")
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL)
        dialog.setCancelable(true)
        //dialog.setOnCancelListener(this)
        dialog.setMax(100)
        dialog.setProgress(0)
        dialog.show()
    }

    override fun doInBackground(vararg params: String?): Long {
        Log.d(TAG, "doInBackground - " + params[0])
        try {
            Log.d(TAG, "Try - " + params[0])
            for (i in 0..9){
                Log.d(TAG, "forEach - " + i)
                if (isCancelled) {
                    Log.d(TAG, "Cancelled!")
                    break
                }
                Thread.sleep(1000)
                publishProgress((i + 1) * 10)
            }
        }catch (e:InterruptedException){
            Log.d(TAG, "InterruptedException in doInBackground")

        }
        return 123L

    }

    override protected fun onProgressUpdate(vararg values: Int?) {
        super.onProgressUpdate(*values)
        Log.d(TAG, "onProgressUpdate - "  + values[0])
        var value:Int = values[0]!!.toInt()
        dialog.setProgress(value)
    }

    override fun onCancelled() {
        super.onCancelled()
        Log.d(TAG, "onCancelled")
        dialog.dismiss()
    }

    override fun onCancel() {

    }

    override fun onCancel(dialog: DialogInterface?) {
        Log.d(TAG, "Dialog onCancell...calling cancel(true)")
        this.cancel(true)
    }

}
