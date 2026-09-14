package com.example.southkoreatravel.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SeoulPlacesOfInterestScreen(onBack: () -> Unit) {
    ScreenScaffold(title = "Places Of Interest", onBack = onBack) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(24.dp)
        ) {
            SectionHeader("Gyeongbokgung Palace")
            BodyText(boldNames("Gyeongbokgung Palace (경복궁) is Seoul's main royal palace, in Sejongno, Jongno-gu — the nearest station is Gyeongbokgung Station (Line 3), Exit 5. Anyone wearing hanbok (traditional Korean clothing) gets in free — a policy that actually applies to all five of Seoul's royal palaces, but Gyeongbokgung is the largest and most iconic choice for it, and hanbok rental shops line the streets right outside the main gate (roughly ₩15,000-20,000 for a two-hour rental). Don't miss the Royal Guard Changing Ceremony at Gwanghwamun Gate, held around 10am and 2pm (closed Tuesdays), the scenic Gyeonghoeru Pavilion on its lotus pond, and the National Folk Museum of Korea, which sits right on the palace grounds.", listOf("Gyeongbokgung Palace", "Royal Guard Changing Ceremony", "Gwanghwamun Gate", "Gyeonghoeru Pavilion", "National Folk Museum of Korea")))

            SectionHeader("Starfield Library")
            BodyText(boldNames("The famous library inside a shopping mall is the Starfield Library (별마당도서관), located inside COEX Mall in Samseong-dong, Gangnam — nearest station Samseong (Line 2), directly connected to the mall. Bookshelves rise about 13 meters across two floors holding roughly 70,000 books. It's free to visit and one of the most photographed spots in Seoul.", listOf("Starfield Library", "COEX Mall")))

            SectionHeader("K-Star Road")
            BodyText(boldNames("K-Star Road runs along Apgujeong-ro in the Apgujeong/Cheongdam area of Gangnam — nearest station Apgujeong Rodeo — and is lined with 17 life-size \"GangnamDol\" bear statues, each decorated in the style of a different K-pop group or agency. It's free, outdoor, and no ticket is needed. Note that SM Entertainment's nearby K-pop experience center, COEX Artium/SUM, permanently closed back in 2020, so it's no longer part of a K-pop-themed visit here.", listOf("K-Star Road", "COEX Artium/SUM")))

            SectionHeader("Museums")
            BodyText(boldNames("The National Museum of Korea (국립중앙박물관) in Yongsan-gu — nearest station Ichon — is the country's flagship, largest museum, covering Korean history and art from prehistoric times to the modern era, with free admission to the permanent collection. One quirk worth knowing: the permanent galleries close on the first Monday of March, April, June, September, November, and December for maintenance.", listOf("National Museum of Korea")))
            BodyText(boldNames("The National Museum of Korean Contemporary History sits right next to Gwanghwamun Square — nearest station Gwanghwamun (Line 5) — and covers Korea's modern history from the late 19th century through today, with free admission and a rooftop observatory overlooking the square. It's a good complement to the National Museum of Korea's broader historical scope.", listOf("National Museum of Korean Contemporary History", "Gwanghwamun Square", "National Museum of Korea")))

            SectionHeader("Jogyesa Temple")
            BodyText(boldNames("Jogyesa Temple (조계사), near Insadong in Jongno-gu (nearest station Anguk), is the head temple of the Jogye Order — effectively the center of Korean Buddhism — and is free and open to the public year-round. It's most spectacular around Buddha's Birthday, when it serves as the finishing point of the UNESCO-recognized Lotus Lantern Festival (Yeondeunghoe) parade, with the temple grounds covered in illuminated lotus-shaped lanterns from around 6pm to midnight during the festival period.", listOf("Jogyesa Temple", "Jogye Order", "Lotus Lantern Festival", "Yeondeunghoe")))

            SectionHeader("Namsan Tower")
            BodyText(boldNames("N Seoul Tower (Namsan Seoul Tower, 남산서울타워) sits atop Namsan Mountain in central Seoul and is one of the city's most iconic sights, with 360-degree panoramic views. The easiest approach is from Myeongdong Station (Line 4) or Chungmuro Station (Lines 3/4) — most visitors ride the Namsan Cable Car up from the Myeongdong side, though several hiking trails (roughly 25-40 minutes) also lead to the top. The observatory ticket runs around ₩29,000 at the gate (cheaper through online resellers), with combo tickets that include the cable car running around ₩49,000 — check current prices before you go, as they shift. The tower is known for its \"Locks of Love\" fences covered in padlocks, the N.GRILL revolving restaurant (completing a full rotation roughly every 48 minutes), and night lighting that changes color, including using blue to signal clean air.", listOf("N Seoul Tower", "Namsan Seoul Tower", "Namsan Mountain", "Namsan Cable Car", "N.GRILL")))

            SectionHeader("K-Pop Square 3D Billboard")
            BodyText(boldNames("Seoul's famous 3D billboard is \"WAVE\" at K-Pop Square, outside COEX Mall in Gangnam — nearest station Samseong (Line 2), Exits 5/6. It's a massive curved LED screen, roughly 80 meters wide and 20 meters tall, created by the Seoul studio d'strict, and is widely described as the world's largest anamorphic illusion display — its signature animation makes a giant ocean wave appear to crash right out of the screen. It's completely free to view, runs 24 hours with content rotating throughout the day, and looks most striking after dark, so aim to arrive around sunset.", listOf("WAVE", "K-Pop Square", "COEX Mall")))

            SectionHeader("Bukchon Hanok Village")
            BodyText(boldNames("Bukchon Hanok Village (북촌한옥마을) sits on the hillside between Gyeongbokgung Palace and Changdeokgung Palace in Jongno-gu — nearest station Anguk (Line 3), Exit 2, about a 600m walk. It's a living residential neighborhood of roughly 900 traditional hanok houses, so visiting hours run 10am to sunset and quiet, respectful visiting is encouraged. It's free to walk through, and the Seoul Metropolitan Government has designated 8 official best-view photo spots around the village. Hanbok rental shops nearby charge roughly $8-10 for a couple of hours, and wearing hanbok also gets you free entry to nearby palaces.", listOf("Bukchon Hanok Village", "Gyeongbokgung Palace", "Changdeokgung Palace")))

            SectionHeader("Insadong")
            BodyText(boldNames("Insadong (인사동), next to Bukchon in Jongno-gu — nearest station Anguk (Line 3), Exit 6, about 100m to the main street — is Seoul's traditional arts and antiques district, home to roughly 90% of the country's traditional stationery shops and about 40% of its antique shops and galleries, along with tea houses and the spiral-shaped Ssamziegil shopping complex. On weekends the main street closes to traffic for market stalls (Saturday 2-10pm, Sunday 10am-10pm).", listOf("Insadong", "Ssamziegil")))

            SectionHeader("Changdeokgung Palace and Secret Garden")
            BodyText(boldNames("Changdeokgung Palace (창덕궁), in Jongno-gu — nearest station Anguk (Line 3), Exit 3, about a 6-minute walk, or Jongno 3-ga Station (Lines 1/3/5), Exit 7 — is a UNESCO World Heritage Site and general admission runs ₩3,000. Behind the palace is the Secret Garden (Huwon, 후원), a rear royal garden accessible only on a guided tour for an extra ₩5,000, with limited daily capacity (roughly 100 spots per time slot: 50 booked online, 50 same-day). Online reservations open at 10am, six days before the visit date, so plan ahead if you want a Secret Garden slot.", listOf("Changdeokgung Palace", "Secret Garden", "Huwon")))

            SectionHeader("DDP (Dongdaemun Design Plaza)")
            BodyText(boldNames("DDP (Dongdaemun Design Plaza, 동대문디자인플라자), in Jung-gu — nearest station Dongdaemun History & Culture Park (Lines 2/4/5), Exit 1, directly adjacent — is a neofuturistic building designed by Zaha Hadid, built from over 40,000 uniquely shaped aluminum panels. General admission to the building and outdoor plaza is free (some special exhibitions charge a separate fee), and it houses exhibition halls, a design museum and lab, and several restaurants. Open 10am-8pm daily except January 1st, Lunar New Year, and Chuseok.", listOf("DDP", "Dongdaemun Design Plaza")))

            SectionHeader("\"Gangnam Style\" Statue")
            BodyText(boldNames("The Gangnam Style statue stands outside COEX Mall in Gangnam-gu — nearest station Samseong (Line 2) — and is a bronze sculpture of two giant crossed hands frozen in PSY's signature horse-riding dance move from his 2012 global hit \"Gangnam Style.\" It's free to visit, and there's an accompanying stage where the song plays so visitors can strike the pose for photos.", listOf("Gangnam Style", "COEX Mall")))

            SectionHeader("Cheonggyecheon Stream")
            BodyText(boldNames("Cheonggyecheon Stream (청계천), the restored waterway running through central Seoul near Gwanghwamun and City Hall — close to the Gwanghwamun branch of Alegria Coffee Roasters — makes for an easy, scenic riverside walk right in the middle of downtown, below street level and away from traffic noise, with stepping stones, small waterfalls, and seasonal lighting displays along the way. It's free and open at all hours.", listOf("Cheonggyecheon Stream", "Alegria Coffee Roasters")))

            SectionHeader("Seoul Forest")
            BodyText(boldNames("Seoul Forest (서울숲), in Seongdong-gu right on the Han River — nearest station Seoul Forest (Bundang Line), Exit 3, or Ttukseom (Line 2), about a 15-minute walk — is a large public park that's free and open 24/7. Its Eco Forest area has a deer corral with vending-machine deer feed, a popular draw for families, and the park rents bikes and pedal karts. It's especially scenic during cherry blossom season in spring and for ginkgo trees in autumn, and connects directly to the Han River park paths.", listOf("Seoul Forest")))

            SectionHeader("Théâtre des Lumières")
            BodyText(boldNames("Théâtre des Lumières, inside the Grand Walkerhill Seoul Hotel in Gwangjin-gu, is an immersive digital-art exhibition space converted from the historic 1963 Walkerhill Grand Theater — 120 projectors and 60 speakers project a large-scale, 360-degree animated show onto the walls and floor. Exhibitions rotate periodically (past runs have included Klimt and Dalí themes; current showings are \"Pharaohs: Egypt\" and a Lee Ungno retrospective), so the specific show you see will depend on when you visit. It's family-friendly with no content-based age restriction — ticket tiers are set by birth year: Adult (born before 2005), Youth (2006-2011), Child (2012-2017), with children under 3 free.", listOf("Théâtre des Lumières", "Grand Walkerhill Seoul Hotel")))
            BodyText(boldNames("It's open daily 10am-7:10pm, last admission 6:15pm. Tickets run roughly ₩29,000 adult / ₩21,000 youth / ₩15,000 child. There's no subway station right at the door — take Line 5 to Gwangnaru Station, Exit 2, or Line 2 to Gangbyeon Station, Exit 1, then a free shuttle bus (roughly every 15 minutes) to the hotel; the venue is on B1. Book in advance on the official site (deslumieres.co.kr) or through resellers like Trazy, Klook, or Trip.com — there's no fixed time slot, so a booked ticket is valid for entry any time during opening hours on the selected date, and cancellations get a full refund up until 5pm the day before your visit.", listOf("Théâtre des Lumières")))
        }
    }
}
