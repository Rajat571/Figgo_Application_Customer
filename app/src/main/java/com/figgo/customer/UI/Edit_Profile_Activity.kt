package com.figgo.customer.UI

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.FileProvider
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.figgo.customer.pearlLib.BaseClass
import com.figgo.customer.BuildConfig
import com.figgo.customer.Model.SpinnerObj
import com.figgo.cabs.figgodriver.Adapter.SpinnerAdapter
import com.figgo.customer.R
import com.figgo.customer.pearlLib.Helper
import de.hdodenhof.circleimageview.CircleImageView
import org.json.JSONObject
import java.io.File
import java.text.SimpleDateFormat
import java.util.*


class Edit_Profile_Activity : BaseClass() {
    var statelist=ArrayList<SpinnerObj>()
    var citylist=ArrayList<SpinnerObj>()
    val TAKE_PIC_REQUEST_CODE = 0
    val CHOOSE_PIC_REQUEST_CODE = 1
    val MEDIA_TYPE_IMAGE = 2
    private val PICK_FROM_GALLERY = 1
    private var mMediaUri: Uri? = null
    var profile: CircleImageView? = null
    var state: CircleImageView? = null
    var spinnerCity: Spinner? = null
    var spinnerState: Spinner? = null
    val statehashMap = HashMap<String, Int>()
    val cityhashMap = HashMap<String, Int>()

    override fun setLayoutXml() {
        TODO("Not yet implemented")
    }

    override fun initializeViews() {
        TODO("Not yet implemented")
    }

    override fun initializeClickListners() {
        TODO("Not yet implemented")
    }

    override fun initializeInputs() {
        TODO("Not yet implemented")
    }

    override fun initializeLabels() {
        TODO("Not yet implemented")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)
        var iv_bellicon = findViewById<ImageView>(R.id.iv_bellicon)
        var img_select_profile = findViewById<ImageView>(R.id.select_profile)
         profile = findViewById<CircleImageView>(R.id.profile)
        spinnerState = findViewById<Spinner>(R.id.update_state)
        spinnerCity = findViewById<Spinner>(R.id.update_city)


        iv_bellicon.setOnClickListener {
            startActivity(Intent(this, NotificationBellIconActivity::class.java))
        }


        img_select_profile.setOnClickListener {
            try {
                if (ActivityCompat.checkSelfPermission(
                        this@Edit_Profile_Activity,
                        android.Manifest.permission.READ_EXTERNAL_STORAGE
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    ActivityCompat.requestPermissions(
                        this@Edit_Profile_Activity,
                        arrayOf(
                            android.Manifest.permission.READ_EXTERNAL_STORAGE,
                            android.Manifest.permission.WRITE_EXTERNAL_STORAGE
                        ),
                        PICK_FROM_GALLERY
                    )
                } else {
                    val builder: AlertDialog.Builder = AlertDialog.Builder(this@Edit_Profile_Activity)
                    builder.setTitle("Upload or Take a photo")
                    builder.setPositiveButton("Gallery",
                        DialogInterface.OnClickListener { dialog, which -> //upload image

                            val intent = Intent()
                            intent.setType("image/*")
                            intent.setAction(Intent.ACTION_GET_CONTENT)
                            startActivityForResult(
                                Intent.createChooser(
                                    intent,
                                    "Select Picture"
                                ), CHOOSE_PIC_REQUEST_CODE
                            )

                          //  val choosePictureIntent = Intent(Intent.ACTION_GET_CONTENT)
                          //  choosePictureIntent.type = "image/*"
                           // startActivityForResult(choosePictureIntent, CHOOSE_PIC_REQUEST_CODE)
                            // mSaveChangesBtn.setEnabled(true)
                        })
                    builder.setNegativeButton("Take Photo",
                        DialogInterface.OnClickListener { dialog, which ->
                            //take photo
                            val takePicture = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                            mMediaUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE)
                            if (mMediaUri == null) {
                                //display error
                                Toast.makeText(
                                    applicationContext,
                                    "Sorry there was an error! Try again.",
                                    Toast.LENGTH_LONG
                                ).show()
                                // mSaveChangesBtn.setEnabled(false)
                            } else {
                                takePicture.putExtra(MediaStore.EXTRA_OUTPUT, mMediaUri)
                                startActivityForResult(takePicture, TAKE_PIC_REQUEST_CODE)
                                //  mSaveChangesBtn.setEnabled(true)
                            }
                        })
                    val dialog: AlertDialog = builder.create()
                    dialog.show()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }



        }


        shareimg()
        onBackPress()

        fetchState()
    }

    private fun getOutputMediaFileUri(mediaTypeImage: Int): Uri? {
        return if (isExternalStorageAvailable()) {
            //get the URI
            //get external storage dir
            val mediaStorageDir =  this@Edit_Profile_Activity.getExternalFilesDir(null);


            if (!mediaStorageDir!!.exists()){
                if (!mediaStorageDir.mkdirs()) {

                    return null;
                }
            }


            //create subdirectore if it does not exist

            //create a file name
            //create file
            var mediaFile: File? = null
            val now = Date()
            val timestamp: String = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(now)
            val path: String = mediaStorageDir.getPath() + File.separator
            if (mediaTypeImage == MEDIA_TYPE_IMAGE) {
                mediaFile = File(path + "IMG_" + timestamp + ".jpg")
            }
            //return file uri
          //  Log.d("UPLOADIMAGE", "FILE: " + Uri.fromFile(mediaFile))
          //  Uri.fromFile(mediaFile)

           FileProvider.getUriForFile(
                this@Edit_Profile_Activity,
                BuildConfig.APPLICATION_ID + ".provider",
               mediaFile!!
            )

        } else {
            null
        }
    }


    private fun isExternalStorageAvailable(): Boolean {
        val state = Environment.getExternalStorageState()
        return if (state == Environment.MEDIA_MOUNTED) {
            true
        } else {
            false
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            if (requestCode == CHOOSE_PIC_REQUEST_CODE) {
                if (data == null) {
                    Toast.makeText(applicationContext, "Image cannot be null!", Toast.LENGTH_LONG)
                        .show()
                } else {
                    mMediaUri = data.data
                    profile?.setImageURI(mMediaUri)
                    //set previews
                   // mPreviewImageView.setImageURI(mMediaUri)

                    //Bundle extras = data.getExtras();

                    //Log.e("URI", mMediaUri.toString());

                    //Bitmap bmp = (Bitmap) extras.get("data");
                  //  Toast.makeText(applicationContext, "Image selected!", Toast.LENGTH_LONG)
                    //    .show()
                }
            } else {
                val mediaScanIntent = Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE)
                profile?.setImageURI(mMediaUri)
              //  mediaScanIntent.data = mMediaUri
               // sendBroadcast(mediaScanIntent)
                //set previews
               // mPreviewImageView.setImageURI(mMediaUri)
            }
        } else if (resultCode != RESULT_CANCELED) {
            Toast.makeText(applicationContext, "Cancelled!", Toast.LENGTH_LONG).show()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            PICK_FROM_GALLERY ->                 // If request is cancelled, the result arrays are empty.
                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    val builder: AlertDialog.Builder = AlertDialog.Builder(this@Edit_Profile_Activity)
                    builder.setTitle("Upload or Take a photo")
                    builder.setPositiveButton("Upload",
                        DialogInterface.OnClickListener { dialog, which -> //upload image
                            val choosePictureIntent = Intent(Intent.ACTION_GET_CONTENT)
                            choosePictureIntent.type = "image/*"
                            startActivityForResult(choosePictureIntent, CHOOSE_PIC_REQUEST_CODE)
                            // mSaveChangesBtn.setEnabled(true)
                        })
                    builder.setNegativeButton("Take Photo",
                        DialogInterface.OnClickListener { dialog, which ->
                            //take photo
                            val takePicture = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                            mMediaUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE)
                            if (mMediaUri == null) {
                                //display error
                                Toast.makeText(
                                    applicationContext,
                                    "Sorry there was an error! Try again.",
                                    Toast.LENGTH_LONG
                                ).show()
                                // mSaveChangesBtn.setEnabled(false)
                            } else {
                                takePicture.putExtra(MediaStore.EXTRA_OUTPUT, mMediaUri)
                                startActivityForResult(takePicture, TAKE_PIC_REQUEST_CODE)
                                //  mSaveChangesBtn.setEnabled(true)
                            }
                        })
                    val dialog: AlertDialog = builder.create()
                    dialog.show()
                } else {
                    //do something like displaying a message that he didn`t allow the app to access gallery and you wont be able to let him select from gallery
                }
        }
    }



    private fun fetchState() {
        statehashMap.clear()
        statelist.clear()
        val URL = Helper.GET_STATE

        val queue = Volley.newRequestQueue(this@Edit_Profile_Activity)
        val json = JSONObject()
        json.put("country_id","101")

        Log.d("SendData", "json===" + json)

        val jsonOblect=  object : JsonObjectRequest(Method.POST, URL, json,
            Response.Listener<JSONObject?> { response ->
                Log.d("SendData", "response===" + response)
                // Toast.makeText(this.requireContext(), "response===" + response,Toast.LENGTH_SHORT).show()
                if (response != null) {
                    val status = response.getString("status")
                    if(status.equals("1")){
                        val jsonArray = response.getJSONArray("states")
                        // statehashMap.put("Select State",0)
                        for (i in 0..jsonArray.length()-1){
                            val rec: JSONObject = jsonArray.getJSONObject(i)
                            var name = rec.getString("name")
                            var id = rec.getString("id")

                            statelist.add(SpinnerObj(name,id))
                            statehashMap.put(name,id.toInt())
                        }

                        //spinner
                        //  val stateadapter =  ArrayAdapter(requireContext(),android.R.layout.simple_spinner_item,statehashMap.keys.toList());
                        Log.d("SendData", "statelist===" + statelist)
                        val stateadapter = SpinnerAdapter(this@Edit_Profile_Activity,statelist)

                        // stateadapter.setDropDownViewResource(R.layout.spinneritemlayout)
                        spinnerState?.setAdapter(stateadapter)


                        spinnerState?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                                var id1 = stateadapter.getItem(position)!!.id.toInt()


                                Log.d("SendData", "id1===" + stateadapter.getItem(position)!!.id.toInt())

                                //binding.selectStateTV.text=statehashMap.keys.toList()[position]
                              //  prefManager.setDriveState(id1!!.toInt())
                                fetchCity(stateadapter.getItem(position)!!.id.toInt())
                            }
                            override fun onNothingSelected(parent: AdapterView<*>?) {
                            }
                        }

                    }else{

                    }

                }

            }, Response.ErrorListener {

            }){}
        queue.add(jsonOblect)

    }

    private fun fetchCity(id: Int) {
        cityhashMap.clear()
        citylist.clear()
        val URL = Helper.GET_CITY
        val queue = Volley.newRequestQueue(this@Edit_Profile_Activity)
        val json = JSONObject()


        json.put("state_id",id)

        Log.d("SendData", "json===" + json)

        val jsonOblect=  object : JsonObjectRequest(Method.POST, URL, json,
            Response.Listener<JSONObject?> { response ->
                Log.d("SendData", "response===" + response)
                // Toast.makeText(this.requireContext(), "response===" + response,Toast.LENGTH_SHORT).show()
                if (response != null) {
                    val status = response.getString("status")
                    if(status.equals("1")){
                        val jsonArray = response.getJSONArray("cities")
                        for (i in 0..jsonArray.length()-1){
                            val rec: JSONObject = jsonArray.getJSONObject(i)
                            var name = rec.getString("name")
                            var id = rec.getString("id")
                            citylist.add(SpinnerObj(name,id))
                            cityhashMap.put(name,id.toInt())

                        }

                        val stateadapter = SpinnerAdapter(this@Edit_Profile_Activity,citylist)
                        spinnerCity?.setAdapter(stateadapter)


                        spinnerCity?.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
                            override fun onItemSelected(adapterView: AdapterView<*>?, view: View, position: Int, id: Long) {

                                val id1 = stateadapter.getItem(position)?.id
                               // prefManager.setDriveCity(id1!!.toInt())
                              //  Log.d("SendData", "cityid===" + id1)

                            }
                            @SuppressLint("SetTextI18n")
                            override fun onNothingSelected(adapter: AdapterView<*>?) {
                            }
                        })
                    }else{

                    }
                    Log.d("SendData", "json===" + json)
                }

            }, Response.ErrorListener {
                // Error
            }){}
        queue.add(jsonOblect)


    }


}