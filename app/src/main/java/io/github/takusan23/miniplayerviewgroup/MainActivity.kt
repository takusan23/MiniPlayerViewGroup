package io.github.takusan23.miniplayerviewgroup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.github.takusan23.miniplayerviewgroup.databinding.ActivityMainBinding
import io.github.takusan23.miniplayerviewgroup.fragment.PlayerFragment
import io.github.takusan23.miniplayerviewgroup.fragment.VideoListFragment

/**
 * Sample App
 * */
class MainActivity : AppCompatActivity() {

    /** ViewBinding */
    private val viewBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)

        // 動画リストのFragmentを置く
        setVideoListFragment()

    }

    private fun setVideoListFragment() {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.activity_main_fragment_list_host_frame_layout, VideoListFragment())
        }.commit()
    }

    /**
     * プレイヤーのFragmentを置く関数
     *
     * [VideoListFragment]参照
     * */
    fun setPlayerFragment() {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.activity_main_fragment_player_host_frame_layout, PlayerFragment())
        }.commit()
    }

    /** BottomNavigationを返す関数 */
    fun getBottomNavigation() = viewBinding.activityMainBottomNavigationView

}