package io.github.takusan23.miniplayerviewgroup.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import io.github.takusan23.miniplayerviewgroup.MainActivity
import io.github.takusan23.miniplayerviewgroup.adapter.VideoListAdapter
import io.github.takusan23.miniplayerviewgroup.databinding.FragmentVideoListBinding

/**
 * 動画一覧を表示するFragment
 * */
class VideoListFragment : Fragment() {

    /** ViewBinding */
    private val viewBinding by lazy { FragmentVideoListBinding.inflate(layoutInflater) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // RecyclerViewで表示する文字列配列を作成
        val titleList = arrayListOf(
            "Cupcake",
            "Donate",
            "Eclair",
            "Froyo",
            "Gingerbread",
            "Honeycomb",
            "Ice Cream Sandwich",
            "Jelly Bean",
            "KitKat",
            "Lollipop",
            "Marshmallow",
            "Nougat",
            "Oreo",
            "Pie",
            "Queen cake",
            "Red Velvet Cake",
        )
        val videoAdapter = VideoListAdapter(titleList) { title ->
            // 一覧の項目を押したときに呼ばれる
            (requireActivity() as? MainActivity)?.setPlayerFragment()
        }

        viewBinding.fragmentListRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = videoAdapter
        }

    }

}