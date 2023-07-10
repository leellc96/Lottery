package com.example.lottery

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    lateinit var btn: Button
    lateinit var txt: TextView
    lateinit var txtGifts: TextView
    val gifts = arrayOf<Int>(1, 1, 3, 5, 9)
    val probability = arrayOf<Int>(1, 30, 130, 180, 250)//各種獎的機率值，最後要除以1000，改變陣列內容值可改變各獎的中獎機率

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        txtGifts = findViewById(R.id.txtGifts)
        btn = findViewById(R.id.btnLottery)
        ShowGifts()
        btn.setOnClickListener {
            var num = 0;
            var index = 0
            var giftstr: String = "對不起！您沒中獎！"
            txt = findViewById(R.id.txtOutput)
            for (i in probability.indices) {
                if (gifts[i] > 0) {//從第1獎開始，判斷各獎的獎品是否抽完
                    num = (1..1000).random()//產生1 到 1000的隨機數
                    if (num <= probability[i]) {
                        giftstr = "恭喜您抽中${i + 1}獎"
                        gifts[i] = gifts[i] - 1
                        break
                    }
                }
            }
            txt.text = giftstr
            var sum = 0;
            for (item in gifts) {
                sum += item
            }
            if (sum == 0) {//gifts的內容值總合為0代表抽獎完畢，將抽獎按鈕致能關閉，不能再按
                btn.setText("抽獎完畢")
                btn.isEnabled = false
            }
            ShowGifts()
        }
    }

    fun ShowGifts() {
        var str: String = "剩下獎品"
        for (i in gifts.indices) {
            str = str + "\n" + (i + 1) + "獎：" + gifts[i].toString() + "個"
        }
        txtGifts.text = str
    }
}