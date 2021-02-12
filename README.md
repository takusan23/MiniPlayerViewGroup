# MiniPlayerViewGroup

たちみどろいどで使っているミニプレイヤーをライブラリにしました。割と頑張った

[![](https://jitpack.io/v/takusan23/MiniPlayerViewGroup.svg)](https://jitpack.io/#takusan23/MiniPlayerViewGroup)


<p align="center">
<img src="https://imgur.com/qgNSlVS.gif" width=300>
</p>

<p align="center">
<img src="https://imgur.com/Jr6fQAU.png" width=200>
<img src="https://imgur.com/zAKo32e.png" width=200>
<img src="https://imgur.com/GweGpdT.png" width=200>
</p>

MotionLayout使えばって？それはそう

## 特徴✨

- MotionLayoutを利用していない
- 速くスワイプ？フリック？したらすぐに移動する(語彙力)
- プレイヤー部分にクリックイベントがあっても動く

## 仕組み
`View#setTranslateY()`とか`View#setTranslateX()`でプレイヤーの乗ってるViewGroupを操作するもの。  
なのでプレイヤー以外のView（動画説明のところとか）は画面外に押し出している。（雑）

プレイヤーのViewは操作中に`View#updateLayoutParams()`を利用して合うようになってるはず。

# サンプルコード

`app`を参照してください。

# 利用方法
## 導入
### build.gradle

`app`フォルダじゃないところにある`build.gradle`を開き、一行足します。

```gradle
allprojects {
    repositories {
        google()
        jcenter()
        // これを足す
        maven { url 'https://jitpack.io' }
    }
}
```

`app`フォルダに入っている`build.gradle`を開き、一行足します。

`1.0`のところは最新バージョンを入れてください。

```gradle
dependencies {
    // これを足す
    implementation 'com.github.takusan23:MiniPlayerViewGroup:1.0'

    // 以下省略
}
```

それと、`ViewBinding`を有効にしたいので3行ぐらい足します（なくてもいいですが`findViewById`書くのが面倒）

```gradle
android {
    // 省略 

    buildFeatures {
        viewBinding true
    }
}
```

### activity_main.xml

`MiniPlayerViewGroup`を置いてレイアウトを作成します。  

`MiniPlayerViewGroup`の中に置く`LinearLayout`は、`android:clickable="true"`と`android:focusable="true"`をつけないと多分動かない。

```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <!-- ライブラリで追加したやつ -->
    <io.github.takusan23.miniplayerviewgroup.MiniPlayerViewGroup
        android:id="@+id/activity_main_miniplayer_viewgroup"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent">

        <!-- これを動かす。View#setTranslateY() とかで。clickable / focusable を true にすればおｋ -->
        <LinearLayout
            android:id="@+id/activity_main_miniplayer_child_linearlayout"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:clickable="true"
            android:focusable="true"
            android:orientation="vertical">

            <!-- プレイヤー。SurfaceViewとか。ミニプレイヤーになるのはこれ -->
            <ImageView
                android:id="@+id/activity_main_player_view"
                android:layout_width="match_parent"
                android:layout_height="250dp"
                android:background="#252525"
                app:srcCompat="@drawable/ic_outline_local_movies_24"
                app:tint="#fff" />

            <!-- その他のView -->
            <TextView
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:padding="10dp"
                android:text="Video Title"
                android:textSize="20sp" />
            
        </LinearLayout>

    </io.github.takusan23.miniplayerviewgroup.MiniPlayerViewGroup>

</androidx.constraintlayout.widget.ConstraintLayout>
```

### MainActivity.kt

数行書かないといけません。

```kotlin
class MainActivity : AppCompatActivity() {

    /** ViewBinding */
    private val viewBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)

        // ミニプレイヤーセットアップ
        viewBinding.activityMainMiniplayerViewgroup.setup(
                viewBinding.activityMainPlayerView, // プレイヤー。SurfaceViewなど
                viewBinding.activityMainMiniplayerChildLinearlayout // プレイヤーが乗っているViewGroup
        )

    }
}
```

これで使えるようになります。やったぜ

### 横画面
横画面用レイアウトを別に作らないといけません。レイアウトエディターのここから作成できます。

![Imgur](https://imgur.com/Ju9wOso.png)


横画面用レイアウトの例です。

```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <!-- ライブラリで追加したやつ -->
    <io.github.takusan23.miniplayerviewgroup.MiniPlayerViewGroup
        android:id="@+id/activity_main_miniplayer_viewgroup"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent">

        <!-- これを動かす。View#setTranslateY() とかで。clickable / focusable を true にすればおｋ -->
        <LinearLayout
            android:id="@+id/activity_main_miniplayer_child_linearlayout"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:clickable="true"
            android:focusable="true"
            android:orientation="horizontal">

            <!-- プレイヤー。SurfaceViewとか。ミニプレイヤーになるのはこれ -->
            <ImageView
                android:id="@+id/activity_main_player_view"
                android:layout_width="350dp"
                android:layout_height="250dp"
                android:background="#252525"
                app:srcCompat="@drawable/ic_outline_local_movies_24"
                app:tint="#fff" />

            <!-- その他のView -->
            <TextView
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:padding="10dp"
                android:text="Video Title"
                android:textSize="20sp" />

        </LinearLayout>

    </io.github.takusan23.miniplayerviewgroup.MiniPlayerViewGroup>

</androidx.constraintlayout.widget.ConstraintLayout>
```

## カスタマイズ

### ミニプレイヤー、通常画面へ切り替える関数

ボタンを押したときにミニプレイヤーにしたいってこともできます。

```kotlin
viewBinding.activityMainPlayerView.setOnClickListener {
    val miniPlayer = viewBinding.activityMainMiniplayerViewgroup
    when (miniPlayer.currentState) {
        MiniPlayerViewGroup.PLAYER_STATE_DEFAULT -> miniPlayer.toMiniPlayer() // 通常→ミニプレイヤー
        MiniPlayerViewGroup.PLAYER_STATE_MINI -> miniPlayer.toDefaultPlayer() // ミニプレイヤー→通常
    }
}
```

### アニメーション実行時間

`toMiniPlayer()`の時間を変えられます。

```kotlin
// 10秒かけて動かす
viewBinding.activityMainMiniplayerViewgroup.durationMs = 10000
viewBinding.activityMainPlayerView.setOnClickListener {
    val miniPlayer = viewBinding.activityMainMiniplayerViewgroup
    when (miniPlayer.currentState) {
        MiniPlayerViewGroup.PLAYER_STATE_DEFAULT -> miniPlayer.toMiniPlayer() // 通常→ミニプレイヤー
        MiniPlayerViewGroup.PLAYER_STATE_MINI -> miniPlayer.toDefaultPlayer() // ミニプレイヤー→通常
    }
}
```

### コールバック

進捗状態コールバックと状態変更コールバックが用意されています。

```kotlin
// 状態変更コールバック
viewBinding.activityMainMiniplayerViewgroup.addOnStateChangeListener { state ->
    val message = when (state) {
        MiniPlayerViewGroup.PLAYER_STATE_DEFAULT -> "Default"
        MiniPlayerViewGroup.PLAYER_STATE_DESTROY -> "Destroy"
        MiniPlayerViewGroup.PLAYER_STATE_MINI -> "Mini"
        else -> "Undefined"
    }
    Toast.makeText(this, "Player = $message", Toast.LENGTH_SHORT).show()
}
// 遷移進捗。ミニプレイヤーになると1に近づく
viewBinding.activityMainMiniplayerViewgroup.addOnProgressListener { progress ->
    println(progress)
}
```

### 全画面

`setup()`関数で第一引数に指定したプレイヤーのサイズを画面いっぱいにします。  
ちなみに横画面時じゃないと動きません。

```kotlin
viewBinding.activityMainMiniplayerFullscreen?.setOnClickListener {
    val miniPlayer = viewBinding.activityMainMiniplayerViewgroup
    if (miniPlayer.isFullScreenMode) {
        // 通常
        viewBinding.activityMainMiniplayerViewgroup.toDefaultScreen()
    } else {
        // フルスクリーンへ
        viewBinding.activityMainMiniplayerViewgroup.toFullScreen()
    }
}
```

### 現在の状態

`currentState`か、`isDefaultScreen()`、`isMiniPlayer()`が使えます。

```kotlin
// 通常画面かどうか
viewBinding.activityMainMiniplayerViewgroup.currentState == MiniPlayerViewGroup.PLAYER_STATE_DEFAULT
viewBinding.activityMainMiniplayerViewgroup.isDefaultScreen()
```

### BottomNavigationViewも一緒に動かす

隠してるっていうか`height`を`1`にしているだけ。

```
viewBinding.activityMainMiniplayerViewgroup.setupBottomNavigation(viewBinding.activityMainBottomNavigationView)
```

# ライセンス

```
Copyright 2021 takusan_23

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at
    http://www.apache.org/licenses/LICENSE-2.0
Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License. 
```