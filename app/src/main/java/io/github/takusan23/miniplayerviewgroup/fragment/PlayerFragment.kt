package io.github.takusan23.miniplayerviewgroup.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import io.github.takusan23.miniplayerviewgroup.MainActivity
import io.github.takusan23.miniplayerviewgroup.databinding.FragmentPlayerBinding

/**
 * ミニプレイヤーのFragment。このFragmentは重ねて使う
 * */
class PlayerFragment : Fragment() {

    /** ViewBinding */
    private val viewBinding by lazy { FragmentPlayerBinding.inflate(layoutInflater) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // プレイヤーの設定をする
        initMiniPlayer()

    }

    /** プレイヤーの用意 */
    private fun initMiniPlayer() {
        // viewBinding.root is MiniPlayerViewGroup == true
        viewBinding.root.setup(viewBinding.fragmentPlayerMiniplayerPlayerImageView, viewBinding.fragmentPlayerMiniplayerChildLinearLayout)
        // BottomNavigationと連動させる場合は
        (requireActivity() as? MainActivity)?.let { main ->
            viewBinding.root.setupBottomNavigation(main.getBottomNavigation())
        }
    }

}