package com.example.italytravel.screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.italytravel.R

/** A photo with its credit line (Wikimedia Commons author + license). */
data class Photo(@DrawableRes val res: Int, val credit: String)

@Composable
fun PlacePhoto(photo: Photo, contentDescription: String) {
    Column(modifier = Modifier.padding(top = 4.dp, bottom = 8.dp)) {
        Image(
            painter = painterResource(photo.res),
            contentDescription = contentDescription,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .clip(RoundedCornerShape(12.dp))
        )
        Text(
            text = photo.credit,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}

/**
 * Photos shown automatically by [PlaceEntry], keyed by the entry's exact name.
 * Places without a suitable free photo are simply not listed.
 */
val placePhotos: Map<String, Photo> = mapOf(
    "Sant'Eustachio Il Caffè" to Photo(R.drawable.cafe_santeustachio, "Sant'Eustachio Il Caffè. Photo: Jerry Michalski, CC BY-SA 2.0, via Wikimedia Commons"),
    "Tazza d'Oro (La Casa del Caffè)" to Photo(R.drawable.cafe_tazza_doro, "La Casa del Caffè Tazza d'Oro. Photo: lienyuan lee, CC BY 3.0, via Wikimedia Commons"),
    "Giolitti" to Photo(R.drawable.cafe_giolitti, "Giolitti. Photo: Chino at en.wikipedia, Public domain, via Wikimedia Commons"),
    "Slow - Specialty Coffee & Pastry Lab" to Photo(R.drawable.cafe_slow, "Corso Vittorio Emanuele II at Largo di Torre Argentina, where Slow is. Photo: Sjaak Kempe from Groningen, The Netherlands, CC BY 2.0, via Wikimedia Commons"),
    "McDonald's" to Photo(R.drawable.ff_mcdonalds, "The first McDonald's in Italy, Rome (opened 1986). Photo: Asdalol, CC0, via Wikimedia Commons"),
    "Old Wild West" to Photo(R.drawable.ff_oww, "An Old Wild West restaurant (Avezzano). Photo: Marica Massaro, CC BY-SA 4.0, via Wikimedia Commons"),
    "Burger King" to Photo(R.drawable.ff_burgerking, "A Burger King restaurant (England). Photo: Elliott Brown, CC BY 2.0, via Wikimedia Commons"),
    "KFC (Kentucky Fried Chicken)" to Photo(R.drawable.ff_kfc, "A KFC restaurant (China). Photo: Dinkun Chen, CC BY-SA 4.0, via Wikimedia Commons"),
    "Pizza al taglio (pizza cut to size)" to Photo(R.drawable.food_pizza_taglio, "Pizza al taglio. Photo: Shoebill2, Public domain, via Wikimedia Commons"),
    "Bombolone" to Photo(R.drawable.food_bombolone, "Bomboloni with jam. Photo: Gail, CC BY 2.0, via Wikimedia Commons"),
    "Carbonara" to Photo(R.drawable.food_carbonara, "Spaghetti alla carbonara. Photo: Javier Somoza, CC BY-SA 4.0, via Wikimedia Commons"),
    "Cornetto" to Photo(R.drawable.food_cornetto, "Cornetto. Photo: Ensahequ, CC BY-SA 4.0, via Wikimedia Commons"),
    "Sfogliatella" to Photo(R.drawable.food_sfogliatella, "Sfogliatella riccia. Photo: Saggittarius A, CC BY-SA 4.0, via Wikimedia Commons"),
    "Cannoli" to Photo(R.drawable.food_cannoli, "Cannoli siciliani. Photo: Paolo Piscolla, CC BY-SA 2.0, via Wikimedia Commons"),
    "Insalata caprese (Caprese salad)" to Photo(R.drawable.food_caprese, "Insalata caprese. Photo: Rainer Zenz, CC BY-SA 3.0, via Wikimedia Commons"),
    "Amatriciana" to Photo(R.drawable.food_amatriciana, "Bucatini all'amatriciana. Photo: Popo le Chien, CC0, via Wikimedia Commons"),
    "Gricia" to Photo(R.drawable.food_gricia, "Rigatoni alla gricia. Photo: Key West Wedding Photography, CC BY 2.0, via Wikimedia Commons"),
    "Rigatoni con la pajata" to Photo(R.drawable.food_pajata, "Rigatoni con la pajata. Photo: Prof.lumacorno, CC BY-SA 4.0, via Wikimedia Commons"),
    "Fettuccine Alfredo" to Photo(R.drawable.food_alfredo, "Fettuccine Alfredo. Photo: Jorgehdezalonso, CC BY-SA 4.0, via Wikimedia Commons"),
    "Pasta e ceci" to Photo(R.drawable.food_pasta_ceci, "Pasta e ceci. Photo: Rollopack, CC BY-SA 4.0, via Wikimedia Commons"),
    "Carciofi alla giudia" to Photo(R.drawable.food_giudia, "Carciofi alla giudia. Photo: Marco Iannantuoni, CC BY-SA 3.0, via Wikimedia Commons"),
    "Fried starters: fiori di zucca, baccalà, aliciotti" to Photo(R.drawable.food_fiori_zucca, "Fried fiori di zucca. Photo: Giaccai, CC BY-SA 3.0, via Wikimedia Commons"),
    "Cacio e pepe" to Photo(R.drawable.food_cacio_pepe, "Cacio e pepe at a trattoria in Rome. Photo: Schellack, CC BY-SA 4.0, via Wikimedia Commons"),
    "Lasagna" to Photo(R.drawable.food_lasagna, "Lasagna. Photo: Marica Massaro, CC BY-SA 4.0, via Wikimedia Commons"),
    "Pasta al ragù and 'bolognese'" to Photo(R.drawable.food_ragu, "Tagliatelle al ragù. Photo: Ivan Vighetto, CC BY-SA 3.0, via Wikimedia Commons"),
    "Polpette" to Photo(R.drawable.food_polpette, "Meatballs in tomato sauce. Photo: Luca Nebuloni from Milan, Italy, CC BY 2.0, via Wikimedia Commons"),
    "Carciofi alla romana (Roman artichokes)" to Photo(R.drawable.food_carciofi, "Carciofi alla romana. Photo: C. Shen, CC BY-SA 3.0, via Wikimedia Commons"),
    "Pecorino Romano" to Photo(R.drawable.food_pecorino, "Pecorino Romano. Photo: Jon Sullivan, Public domain, via Wikimedia Commons"),
    "L'Antico Forno di Fontana di Trevi" to Photo(R.drawable.forno_trevi, "Via delle Muratte, the street of L'Antico Forno. Photo: Mister No, CC BY 3.0, via Wikimedia Commons"),
    "Red Pizza - Trastevere" to Photo(R.drawable.forno_red, "Via della Scala, Trastevere, the street of Red Pizza. Photo: Jensens, Public domain, via Wikimedia Commons"),
    "Mama Eat" to Photo(R.drawable.gf_mama_eat, "Via di San Cosimato, Trastevere, the street of Mama Eat. Photo: Dguendel, CC BY 3.0, via Wikimedia Commons"),
    "New Food Gluten Free - Ponte Sisto" to Photo(R.drawable.gf_new_food, "Ponte Sisto - New Food is at its city-center end. Photo: Livioandronico2013, CC BY-SA 4.0, via Wikimedia Commons"),
    "Fiocco di Neve" to Photo(R.drawable.ice_fiocco, "Piazza della Rotonda at the Pantheon - Fiocco di Neve is a minute away. Photo: Silvio Pietrosanti, CC BY-SA 4.0, via Wikimedia Commons"),
    "Fiocco di Neve (gelato)" to Photo(R.drawable.ice_fiocco, "Piazza della Rotonda at the Pantheon - Fiocco di Neve is a minute away. Photo: Silvio Pietrosanti, CC BY-SA 4.0, via Wikimedia Commons"),
    "MYO - La Gelateria" to Photo(R.drawable.ice_myo, "Campo de' Fiori, where MYO is. Photo: Fred Romero from Paris, France, CC BY 2.0, via Wikimedia Commons"),
    "MYO - La Gelateria (gelato)" to Photo(R.drawable.ice_myo, "Campo de' Fiori, where MYO is. Photo: Fred Romero from Paris, France, CC BY 2.0, via Wikimedia Commons"),
    "Grom" to Photo(R.drawable.ice_grom, "A Grom gelateria (Cuneo). Photo: Betty&Giò, CC BY-SA 4.0, via Wikimedia Commons"),
    "Grom (gelato)" to Photo(R.drawable.ice_grom, "A Grom gelateria (Cuneo). Photo: Betty&Giò, CC BY-SA 4.0, via Wikimedia Commons"),
    "StarBaps" to Photo(R.drawable.kor_starbaps, "Via di Santa Maria Maggiore, the street of StarBaps. Photo: Jorge Franganillo, CC BY 2.0, via Wikimedia Commons"),
    "Galbi" to Photo(R.drawable.kor_galbi, "Piazza Fiume, near Galbi. Photo: Agalle, CC BY-SA 3.0, via Wikimedia Commons"),
    "Zara" to Photo(R.drawable.shop_zara, "Via del Corso 189 (Palazzo Bocconi), the Zara flagship. Photo: Anil Öztas, CC BY 4.0, via Wikimedia Commons"),
    "Uniqlo" to Photo(R.drawable.shop_uniqlo, "Galleria Alberto Sordi, home of Uniqlo Via del Corso. Photo: Pymouss, CC BY-SA 4.0, via Wikimedia Commons"),
    "OVS" to Photo(R.drawable.shop_ovs, "An OVS store. Photo: Air fans, CC0, via Wikimedia Commons"),
    "Deichmann" to Photo(R.drawable.shop_deichmann, "A Deichmann store. Photo: WrS.tm.pl, Public domain, via Wikimedia Commons"),
    "Normal" to Photo(R.drawable.shop_normal, "A Normal store (Finland). Photo: Ypsilon from Finland, CC0, via Wikimedia Commons"),
    "Bialetti" to Photo(R.drawable.shop_bialetti, "The Bialetti Moka Express. Photo: Bialetti Industrie, CC BY-SA 4.0, via Wikimedia Commons"),
    "La Feltrinelli" to Photo(R.drawable.shop_feltrinelli, "La Feltrinelli bookshop, Viale Libia, Rome. Photo: Blackcat, CC BY-SA 4.0, via Wikimedia Commons"),
    "Porta di Roma" to Photo(R.drawable.mall_porta_di_roma, "Porta di Roma. Photo: CAPTAIN RAJU, CC BY-SA 4.0, via Wikimedia Commons"),
    "Euroma2" to Photo(R.drawable.mall_euroma2, "Euroma2. Photo: CAPTAIN RAJU, CC BY-SA 4.0, via Wikimedia Commons"),
    "Campo de' Fiori" to Photo(R.drawable.attr_campo, "The Campo de' Fiori market. Photo: Jorge Royan, CC BY-SA 3.0, via Wikimedia Commons"),
    "Pantheon" to Photo(R.drawable.attr_pantheon, "The Pantheon. Photo: Krzysztof Golik, CC BY-SA 4.0, via Wikimedia Commons"),
    "Vatican Museums and Sistine Chapel" to Photo(R.drawable.attr_vatican_museums, "The Sistine Chapel. Photo: Antoine Taveneaux, CC BY-SA 3.0, via Wikimedia Commons"),
    "St. Peter's Basilica and Dome" to Photo(R.drawable.attr_st_peters, "St. Peter's Basilica. Photo: Alvesgaspar, CC BY-SA 4.0, via Wikimedia Commons"),
    "Colosseum" to Photo(R.drawable.attr_colosseum, "The Colosseum. Photo: Diliff, CC BY-SA 2.5, via Wikimedia Commons"),
    "The park" to Photo(R.drawable.attr_villa_borghese, "The lake and Temple of Aesculapius, Villa Borghese. Photo: Krzysztof Golik, CC BY-SA 4.0, via Wikimedia Commons"),
    "Galleria Borghese" to Photo(R.drawable.attr_galleria_borghese, "Galleria Borghese. Photo: Alessio Damato, CC BY-SA 3.0, via Wikimedia Commons"),
    "Trastevere" to Photo(R.drawable.attr_trastevere, "Trastevere. Photo: Alessandro Cossu, CC BY-SA 4.0, via Wikimedia Commons"),
    "Piazza Navona" to Photo(R.drawable.attr_navona, "Piazza Navona and the Fountain of the Four Rivers. Photo: This Photo was taken by Wolfgang Moroder. Feel free to use my photos, but ple, CC BY-SA 3.0, via Wikimedia Commons"),
    "Trevi Fountain (Fontana di Trevi)" to Photo(R.drawable.attr_trevi, "The Trevi Fountain. Photo: Wilfredor, CC0, via Wikimedia Commons"),
    "Bocca della Verità (Mouth of Truth)" to Photo(R.drawable.attr_bocca, "The Bocca della Verità. Photo: Sailko, CC BY 3.0, via Wikimedia Commons"),
    "The neighborhood" to Photo(R.drawable.attr_ghetto, "The Portico of Octavia, Jewish Ghetto. Photo: Joris1919, CC BY-SA 3.0, via Wikimedia Commons"),
    "Great Synagogue and Jewish Museum of Rome" to Photo(R.drawable.attr_synagogue, "The Great Synagogue of Rome. Photo: Livioandronico2013, CC BY-SA 4.0, via Wikimedia Commons"),
    "Planetario di Roma (Planetarium)" to Photo(R.drawable.attr_planetarium, "Entrance of the Museo della Civiltà Romana building, home of the Planetarium. Photo: Jean-Pierre Dalbéra from Paris, France, CC BY 2.0, via Wikimedia Commons"),
    "Museo Civico di Zoologia (Zoology Museum)" to Photo(R.drawable.attr_zoology, "Museo Civico di Zoologia. Photo: Pierfelice Licitra, CC BY 3.0, via Wikimedia Commons"),
    "Museum of Illusions Rome" to Photo(R.drawable.attr_illusions, "A Museum of Illusions room (Toronto branch of the chain). Photo: Niaz Abbas, Public domain, via Wikimedia Commons"),
    "Explora - Museo dei Bambini di Roma" to Photo(R.drawable.attr_explora, "Explora - Museo dei Bambini di Roma. Photo: Albarubescens, CC BY-SA 4.0, via Wikimedia Commons"),
    "San Giovanni in Laterano" to Photo(R.drawable.attr_laterano, "San Giovanni in Laterano. Photo: Palickap, CC BY-SA 4.0, via Wikimedia Commons"),
    "Santa Maria Maggiore" to Photo(R.drawable.attr_sm_maggiore, "Santa Maria Maggiore (archive photo). Photo: Henry A. Millon, Public domain, via Wikimedia Commons"),
    "San Paolo fuori le Mura (St. Paul Outside the Walls)" to Photo(R.drawable.attr_san_paolo, "San Paolo fuori le Mura. Photo: Wikimedia Commons user, CC BY 4.0, via Wikimedia Commons"),
    "Piazza Venezia" to Photo(R.drawable.attr_venezia, "Piazza Venezia and the Vittoriano. Photo: Paolo Costa Baldi, CC BY-SA 3.0, via Wikimedia Commons"),
    "Piazza del Popolo" to Photo(R.drawable.attr_popolo, "Piazza del Popolo. Photo: This Photo was taken by Wolfgang Moroder. Feel free to use my photos, but ple, CC BY-SA 3.0, via Wikimedia Commons"),
    "Piazza Navona - the main Christmas market" to Photo(R.drawable.attr_navona_xmas, "The Christmas market in Piazza Navona. Photo: Nicholas Gemini, CC BY-SA 3.0, via Wikimedia Commons"),
    "Roman Forum and Palatine Hill" to Photo(R.drawable.attr_forum, "The Roman Forum. Photo: Stefano Lovato from Bologna, Italia, CC BY 2.0, via Wikimedia Commons"),
    "Appian Way (Via Appia Antica)" to Photo(R.drawable.attr_appia, "The Appian Way. Photo: Kleuske at Dutch Wikipedia, CC BY-SA 3.0, via Wikimedia Commons"),
    "Cinecittà Studios (Cinecittà si Mostra)" to Photo(R.drawable.trip_cinecitta, "The entrance of Cinecittà Studios. Photo: JRibaX, CC BY-SA 3.0, via Wikimedia Commons"),
    "Cinecittà World (theme park)" to Photo(R.drawable.trip_cinecitta_world, "A roller coaster at Cinecittà World. Photo: Daniele0601, CC BY-SA 4.0, via Wikimedia Commons"),
    "Villa d'Este" to Photo(R.drawable.trip_villa_deste, "The Hundred Fountains, Villa d'Este. Photo: Albarubescens, CC BY-SA 4.0, via Wikimedia Commons"),
    "Villa Gregoriana" to Photo(R.drawable.trip_villa_gregoriana, "The Great Waterfall, Villa Gregoriana. Photo: Lalupa, Public domain, via Wikimedia Commons"),
    "Villa Adriana (Hadrian's Villa)" to Photo(R.drawable.trip_villa_adriana, "The Canopus, Villa Adriana. Photo: Carole Raddato from FRANKFURT, Germany, CC BY-SA 2.0, via Wikimedia Commons"),
    "Lake Albano" to Photo(R.drawable.trip_albano, "Lake Albano. Photo: Livioandronico2013, CC BY-SA 3.0, via Wikimedia Commons"),
    "Papal Palace and Gardens of Castel Gandolfo" to Photo(R.drawable.trip_castel_gandolfo, "The Papal Palace, Castel Gandolfo. Photo: Livioandronico2013, CC BY-SA 4.0, via Wikimedia Commons"),
    "Lake Bracciano" to Photo(R.drawable.trip_bracciano, "Lake Bracciano. Photo: Fiat 500e, CC BY 4.0, via Wikimedia Commons"),
    "Castello Orsini-Odescalchi (Bracciano)" to Photo(R.drawable.trip_odescalchi, "Castello Orsini-Odescalchi, Bracciano. Photo: Livioandronico2013, CC BY-SA 4.0, via Wikimedia Commons"),
    "Lake Nemi and the village of Nemi" to Photo(R.drawable.trip_nemi, "Lake Nemi. Photo: Livioandronico2013, CC BY-SA 3.0, via Wikimedia Commons"),
    "Museo delle Navi Romane (Roman Ships Museum)" to Photo(R.drawable.trip_nemi_museo, "Entrance of the Roman Ships Museum, Nemi. Photo: Blackcat, CC BY-SA 3.0 it, via Wikimedia Commons"),
    "Lido di Ostia (Rome's beach)" to Photo(R.drawable.trip_ostia_lido, "The beach at Lido di Ostia. Photo: Panek, CC BY-SA 4.0, via Wikimedia Commons"),
    "Ostia Antica (ancient Roman port)" to Photo(R.drawable.trip_ostia_antica, "The theatre of Ostia Antica. Photo: Marie-Lan Nguyen (User:Jastrow), CC BY 2.5, via Wikimedia Commons"),
    "Fiumicino Airport (FCO)" to Photo(R.drawable.tr_fiumicino, "Leonardo Express trains at Fiumicino Airport station. Photo: Spaghettifier, CC BY-SA 4.0, via Wikimedia Commons"),
    "Ciampino Airport (CIA)" to Photo(R.drawable.tr_ciampino, "The Ciampino Airlink bus at Ciampino Airport. Photo: CAPTAIN RAJU, CC0, via Wikimedia Commons"),
    "Roma Termini station" to Photo(R.drawable.tr_termini, "Inside Roma Termini station. Photo: Unknown, Public domain, via Wikimedia Commons"),
    "Metro, buses and trams" to Photo(R.drawable.tr_metro, "A Rome Metro train (Line A, Anagnina). Photo: Andreas., CC BY-SA 2.0, via Wikimedia Commons"),
    "Taxis and taxi stands" to Photo(R.drawable.tr_taxi, "A Rome taxi sign. Photo: אני ערכתי, CC BY-SA 3.0, via Wikimedia Commons"),
    "Coop" to Photo(R.drawable.sm_coop, "A Coop supermarket (Carrara). Photo: Balfabio, CC BY-SA 3.0, via Wikimedia Commons"),
    "Carrefour" to Photo(R.drawable.sm_carrefour, "A Carrefour Express city store. Photo: Cristian1989, CC BY-SA 4.0, via Wikimedia Commons"),
    "Pam" to Photo(R.drawable.sm_pam, "A Pam supermarket (Pisa). Photo: kajikawa, CC BY 3.0, via Wikimedia Commons"),
    "Conad" to Photo(R.drawable.sm_conad, "A Conad supermarket (San Giovanni Valdarno). Photo: Walter Giannetti, CC BY-SA 4.0, via Wikimedia Commons"),
    "Bioparco di Roma (Rome Zoo)" to Photo(R.drawable.attr_bioparco, "A lion statue on the historic entrance of the Bioparco. Photo: Krzysztof Golik, CC BY-SA 4.0, via Wikimedia Commons"),
)
