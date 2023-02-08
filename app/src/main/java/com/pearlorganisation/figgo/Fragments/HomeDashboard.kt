package com.pearlorganisation.figgo.Fragments

import android.app.Activity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.pearlorganisation.figgo.Adapter.CabCategoryAdapter
import com.pearlorganisation.figgo.Adapter.CardView_Offer_Adapter
import com.pearlorganisation.figgo.Adapter.CardofferAdpter
import com.pearlorganisation.figgo.Adapter.FiggoAddAdapter
import com.pearlorganisation.figgo.Model.CabCategory
import com.pearlorganisation.figgo.Model.CardOfferCashBackList
import com.pearlorganisation.figgo.Model.CurrentVehicle_itemListAdapter
import com.pearlorganisation.figgo.Model.FiggoAdd
import com.pearlorganisation.figgo.R
import java.util.*
import kotlin.collections.ArrayList


class HomeDashboard : Fragment() {
    val duration = 1000
    val pixelsToMove = 30
    private val mHandler: Handler = Handler(Looper.getMainLooper())
    private val mHandler2: Handler = Handler(Looper.getMainLooper())
    private val linearSnap = LinearSnapHelper()
    private val linearSnap2 = LinearSnapHelper()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_home_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       // val recyclerView: RecyclerView = view.findViewById(R.id.figgo_add_list)
        var cabCategoryAdapter: CabCategoryAdapter
        var cab_category_list=ArrayList<CabCategory>()
        var cardoffer_list=ArrayList<CurrentVehicle_itemListAdapter>()

        var cardofferAdpter:CardofferAdpter
        var cardcashbacklist=ArrayList<CardOfferCashBackList>()

        lateinit var cardviewOfferAdapter: CardView_Offer_Adapter


        val figgoAddList = view.findViewById<RecyclerView>(R.id.figgo_add_list)
        var cabCategoryList = view.findViewById<RecyclerView>(R.id.cab_category_list)
        var rcv_cardoffer = view.findViewById<RecyclerView>(R.id.rcv_cardoffer)
        var recyclerview_offerlist = view.findViewById<RecyclerView>(R.id.recyclerview_offerlist)

        val SCROLLING_RUNNABLE: Runnable = object : Runnable {
            override fun run() {
                figgoAddList.smoothScrollBy(pixelsToMove, 0)
                mHandler.postDelayed(this, duration.toLong())
            }
        }
        val SCROLLING_RUNNABLE2: Runnable = object : Runnable {
            override fun run() {
                recyclerview_offerlist.smoothScrollBy(pixelsToMove, 0)
                mHandler2.postDelayed(this, duration.toLong())
            }
        }

        lateinit var figgoAddAdapter: FiggoAddAdapter
        var figgoAddList1=ArrayList<FiggoAdd>()


        figgoAddList.layoutManager= LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,false)
        figgoAddAdapter= FiggoAddAdapter(figgoAddList1)
      //  recycler.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        //recycler.adapter=figgoAddAdapter
        figgoAddList.adapter=figgoAddAdapter
       linearSnap.attachToRecyclerView(figgoAddList)

        Timer().schedule(object : TimerTask() {
            override fun run() {
                if ((figgoAddList.layoutManager as LinearLayoutManager)!!.findFirstCompletelyVisibleItemPosition() < figgoAddAdapter!!.itemCount - 1) {
                    figgoAddList.layoutManager!!.smoothScrollToPosition(
                        figgoAddList,
                        RecyclerView.State(),
                        (figgoAddList.layoutManager as LinearLayoutManager)!!.findFirstCompletelyVisibleItemPosition() + 1
                    )
                } else if ((figgoAddList.layoutManager as LinearLayoutManager)!!.findFirstCompletelyVisibleItemPosition() < figgoAddAdapter!!.itemCount - 1) {
                    figgoAddList.layoutManager!!.smoothScrollToPosition(figgoAddList, RecyclerView.State(), 0)
                }else{
                    figgoAddList.smoothScrollToPosition(0);
                }
            }
        },0, 2000)
        /*cardoffer.add(CardOffer_List("tv_offer","carddesign"))
        cardoffer.add(CardOffer_List("tv_offer","carddesign"))
        cardoffer.add(CardOffer_List("tv_offer","carddesign"))
        cardoffer.add(CardOffer_List("tv_offer","carddesign"))
        cardoffer.add(CardOffer_List("tv_offer","carddesign"))*/



       /* cardviewOfferAdapter= CardView_Offer_Adapter(requireActivity(),cardoffer)
        recyclerView.adapter=cardviewOfferAdapter
        recyclerView.layoutManager=LinearLayoutManager(requireContext())
*/

        figgoAddList1.add(FiggoAdd(R.drawable.army))
        figgoAddList1.add(FiggoAdd(R.drawable.bpl_img))
        figgoAddList1.add(FiggoAdd(R.drawable.oldage_img))
        figgoAddList1.add(FiggoAdd(R.drawable.child_img))
        figgoAddList1.add(FiggoAdd(R.drawable.student_img))

        cabCategoryList.layoutManager= GridLayoutManager(requireContext(),3)
        cab_category_list.add(CabCategory(R.drawable.airportcab,"Airpot-Cab"))
        cab_category_list.add(CabCategory(R.drawable.royalcab,"Royal-cab"))
        cab_category_list.add(CabCategory(R.drawable.fadetour,"Tour-plan"))
        cab_category_list.add(CabCategory(R.drawable.goodsparcel,"Goods vechile"))
        cab_category_list.add(CabCategory(R.drawable.fadehote,"Hotel"))
        cab_category_list.add(CabCategory(R.drawable.airportcab12,"Flight"))
        cab_category_list.add(CabCategory(R.drawable.fadetrain,"Train"))
        cab_category_list.add(CabCategory(R.drawable.fadebus,"Micro Bus"))
        cab_category_list.add(CabCategory(R.drawable.fademore,"More"))
        rcv_cardoffer.layoutManager= GridLayoutManager(requireContext(),3)

        cardoffer_list.add(CurrentVehicle_itemListAdapter(R.drawable.citycab,"City-Cab"))
        cardoffer_list.add(CurrentVehicle_itemListAdapter(R.drawable.outstationcab,"Outstation"))
        cardoffer_list.add(CurrentVehicle_itemListAdapter(R.drawable.fadesharecab,"Share-Cab"))

        cabCategoryAdapter= CabCategoryAdapter(requireContext(),cab_category_list)
        cabCategoryList.adapter=cabCategoryAdapter
        cardviewOfferAdapter= CardView_Offer_Adapter(requireContext() as Activity,cardoffer_list)
        rcv_cardoffer.adapter=cardviewOfferAdapter


        cardcashbacklist.add(CardOfferCashBackList("50 % Cash Back on HDFC Card","Book Now"))
        cardcashbacklist.add(CardOfferCashBackList("50 % Cash Back on HDFC Card","Book Now"))
        cardcashbacklist.add(CardOfferCashBackList("50 % Cash Back on HDFC Card","Book Now"))
        cardcashbacklist.add(CardOfferCashBackList("50 % Cash Back on HDFC Card","Book Now"))
        cardcashbacklist.add(CardOfferCashBackList("50 % Cash Back on HDFC Card","Book Now"))

        recyclerview_offerlist.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        cardofferAdpter=CardofferAdpter(recyclerview_offerlist,cardcashbacklist)
        recyclerview_offerlist.adapter=cardofferAdpter
        recyclerview_offerlist.adapter

        linearSnap2.attachToRecyclerView(recyclerview_offerlist)

        Timer().schedule(object : TimerTask() {
            override fun run() {
                if ((recyclerview_offerlist.layoutManager as LinearLayoutManager)!!.findFirstCompletelyVisibleItemPosition() < cardofferAdpter!!.itemCount - 1) {
                    recyclerview_offerlist.layoutManager!!.smoothScrollToPosition(
                        recyclerview_offerlist,
                        RecyclerView.State(),
                        (recyclerview_offerlist.layoutManager as LinearLayoutManager)!!.findFirstCompletelyVisibleItemPosition() + 1
                    )
                } else if ((recyclerview_offerlist.layoutManager as LinearLayoutManager)!!.findFirstCompletelyVisibleItemPosition() < cardofferAdpter!!.itemCount - 1) {
                    recyclerview_offerlist.layoutManager!!.smoothScrollToPosition(recyclerview_offerlist, RecyclerView.State(), 0)
                }else{
                    recyclerview_offerlist.smoothScrollToPosition(0);
                }
            }
        },0, 2000)

    }

}