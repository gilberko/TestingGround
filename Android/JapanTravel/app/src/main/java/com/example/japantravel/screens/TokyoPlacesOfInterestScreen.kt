package com.example.japantravel.screens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TokyoPlacesOfInterestScreen(onBack: () -> Unit) {
    ScreenScaffold(title = "Places of Interest", onBack = onBack) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            SectionHeader("Nature places")
            BodyText(
                "Shinjuku Gyoen National Garden combines three garden styles in one huge park - " +
                    "a formal French garden, an English landscape garden, and a traditional " +
                    "Japanese strolling garden - plus a large greenhouse. It's one of Tokyo's best " +
                    "cherry blossom spots, has a small entrance fee, and (unlike many city parks) " +
                    "bans alcohol, which keeps it calm and family-friendly."
            )
            BodyText(
                "Rikugien Garden in Bunkyo is an Edo-period strolling garden built around a " +
                    "central pond, laid out to recreate scenes from classical poetry. It's quieter " +
                    "and less crowded than Shinjuku Gyoen, and especially famous for its autumn " +
                    "foliage evening light-up."
            )
            BodyText(
                "Hamarikyu Gardens, near Tsukiji and Shiodome, was once a shogunal duck-hunting " +
                    "ground. Its central pond is filled with seawater and rises and falls with the " +
                    "tide, and a teahouse sits on a small island reached by a wooden bridge, where " +
                    "you can stop for matcha and a sweet. A scenic water bus also runs from Asakusa " +
                    "straight into the garden along the Sumida River."
            )
            BodyText(
                "Yoyogi Park, next to Meiji Jingu's forested shrine grounds, is a big open green " +
                    "space popular for picnics, street performers, and weekend markets - a good " +
                    "contrast to Meiji Jingu's quieter, more solemn forest walk (covered in more " +
                    "detail elsewhere in this app)."
            )

            SectionHeader("Places with a view")
            BodyText(
                "Tokyo Skytree, at 634m the tallest structure in Japan, has two observation " +
                    "decks - the Tembo Deck at 350m and the higher Tembo Galleria at 450m, which " +
                    "includes a sloped glass walkway. At its base, the Tokyo Solamachi mall holds " +
                    "the Sumida Aquarium and a planetarium, so it's easy to combine with other " +
                    "stops."
            )
            BodyText(
                "Shibuya Sky is an open-air rooftop observatory atop Shibuya Scramble Square, " +
                    "229m up, with an unobstructed 360-degree view straight down onto Shibuya " +
                    "Crossing. Being outdoors (rather than behind glass) makes it a favorite for " +
                    "photos, but it also means it closes during bad weather."
            )
            BodyText(
                "The Tokyo Metropolitan Government Building in Shinjuku has twin observatories " +
                    "around 202m up - and they're normally free, which makes this the best-value " +
                    "view in the city (occasional closures for renovation or events do happen, so " +
                    "it's worth checking ahead)."
            )
            BodyText(
                "Tokyo Tower, the red-and-white lattice tower in Minato built in 1958 and modeled " +
                    "on the Eiffel Tower, offers the Main Deck at 150m and the Top Deck at 250m. " +
                    "It's more of a nostalgic, retro-Tokyo view than Skytree's ultra-modern one. " +
                    "Roppongi Hills' Mori Tower is another option, with an indoor observatory " +
                    "around 250m combined with the Mori Art Museum."
            )

            SectionHeader("Kids playgrounds")
            BodyText(
                "Kasai Rinkai Park, out by Tokyo Bay, has large playground equipment, wide lawns " +
                    "to run around on, a Ferris wheel, and the Tokyo Sea Life Park aquarium all in " +
                    "the same area - an easy full afternoon for younger kids without much walking " +
                    "between stops."
            )
            BodyText(
                "Hanegi Playpark in Setagaya is a good example of Japan's \"play park\" (purei " +
                    "pāku) concept - an intentionally unstructured adventure playground with mud, " +
                    "hand tools, rope swings, and sometimes a supervised fire pit, staffed by " +
                    "trained \"playworkers\" who let kids build and experiment rather than follow " +
                    "fixed rules. It's free and a striking contrast to standard fixed-equipment " +
                    "playgrounds."
            )
            BodyText(
                "For something calmer, the open lawns of Shinjuku Gyoen and Yoyogi Park (see " +
                    "Nature places above) both work well for younger kids who just need space to " +
                    "run."
            )

            SectionHeader("Kids museums")
            BodyText(
                "KidZania Tokyo, inside LaLaport Toyosu, is a miniature role-play city where kids " +
                    "try out real jobs - firefighter, pilot, chef, TV reporter, and dozens more - " +
                    "using scaled-down, kid-sized versions of the real equipment and earning play " +
                    "currency as they go. Sessions run in timed blocks and are popular, so advance " +
                    "booking is strongly recommended."
            )
            BodyText(
                "The Tokyo Metro Museum in Kasai puts a real retired subway car on display and " +
                    "has train-driving simulators kids can operate themselves - a hit for any " +
                    "train-obsessed child, and it's right by Kasai Rinkai Park if you want to " +
                    "combine the two."
            )
            BodyText(
                "The Tokyo Fire Museum in Yotsuya (Shinjuku) is free, with historic fire engines, " +
                    "a fire helicopter on the top floor, and hands-on exhibits - a quick, low-cost " +
                    "stop that works well as a shorter add-on to a bigger day out."
            )

            SectionHeader("Science museums")
            BodyText(
                "Miraikan (the National Museum of Emerging Science and Innovation) on Odaiba " +
                    "focuses on cutting-edge technology and robotics, including regular " +
                    "demonstrations with humanoid robots, plus a planetarium and exhibits on " +
                    "space, AI, and biology - built to be interactive rather than just look-and-" +
                    "read."
            )
            BodyText(
                "The National Museum of Nature and Science (Kahaku) in Ueno Park is Japan's " +
                    "premier natural history museum, with dinosaur skeletons, a huge blue whale " +
                    "model outside the entrance, and exhibits spanning space, earth science, and " +
                    "Japanese natural history - easy to combine with Ueno Zoo and Ueno's other " +
                    "museums in the same park."
            )
            BodyText(
                "The Science Museum in Kitanomaru Park (near the Budokan) is an older, more " +
                    "hands-on-experiment style museum - less flashy than Miraikan, but with plenty " +
                    "of physical, kid-operable exhibits across its floors."
            )

            SectionHeader("Aquariums")
            BodyText(
                "Sumida Aquarium, inside the Tokyo Solamachi mall at the base of Skytree, is known " +
                    "for its open-air, glass-free penguin and fur seal pools and a large jellyfish " +
                    "gallery - convenient to pair with a Skytree visit since they share the same " +
                    "building."
            )
            BodyText(
                "Sunshine Aquarium sits on the rooftop of Sunshine City in Ikebukuro, where a " +
                    "donut-shaped tank overhead - the \"Sunshine Aqua Ring\" - makes penguins " +
                    "appear to fly above visitors' heads. It's a compact but memorable rooftop " +
                    "aquarium, and Sunshine City itself has plenty else to fill out a day."
            )
            BodyText(
                "Maxell Aqua Park Shinagawa, right next to Shinagawa Station, mixes dolphin " +
                    "performances with projection mapping, lighting, and music for a more " +
                    "theatrical, nighttime-friendly show than a typical aquarium."
            )
            BodyText(
                "Tokyo Sea Life Park, in the donut-shaped glass dome at Kasai Rinkai Park, is " +
                    "famous for its huge doughnut-shaped tank of continuously circling bluefin " +
                    "tuna - and, as above, sits right next to a playground and Ferris wheel."
            )
            BodyText(
                "Art Aquarium Museum Ginza, on the 9th floor of the Mitsukoshi Ginza building " +
                    "(about 3 minutes' walk from Ginza Station), isn't a conventional aquarium - " +
                    "it's a living-art installation of over 5,000 goldfish across a hundred " +
                    "varieties, staged with light, sound, and scent design, including a " +
                    "\"Goldfish Corridor\" of tall columnar tanks you walk through. It's rooted " +
                    "in the goldfish-appreciation culture that began in Japan's Edo period, and " +
                    "pairs well with a Ginza shopping stop (see City Regions - Ginza)."
            )

            SectionHeader("Korean pop culture (K-pop & K-dramas)")
            BodyText(
                "Shin-Okubo, Tokyo's Koreatown just north of Shinjuku Station (see City Regions " +
                    "- Shinjuku), is the center of K-pop and K-drama fandom in the city. Hanryu " +
                    "Plaza is a multi-story complex packed with CDs, photobooks, posters, trading " +
                    "cards, and official fan lightsticks; Idol Park, just 2 minutes from the " +
                    "station, has a similarly deep stock of merch and reasonably priced CDs, plus " +
                    "an attached cafe to rest in. The neighborhood's shift toward Korean pop " +
                    "culture goods traces back to the early-2000s \"Korean Wave\" (Hallyu) boom " +
                    "driven by Korean TV dramas."
            )
            BodyText(
                "Beyond shopping, Shin-Okubo is also the place for Korean street food - " +
                    "tteokbokki, Korean fried chicken, cheese corn dogs - alongside trendy " +
                    "bubble-tea and dessert cafes, making it easy to combine a few hours of " +
                    "browsing with a meal."
            )

            SectionHeader("J-pop")
            BodyText(
                "Tower Records Shibuya is the flagship of Japan's still-independent Tower " +
                    "Records chain (unrelated to the now-defunct US company) - 9 floors of " +
                    "physical music with a deep J-pop and anime-song selection, plus a 2nd-floor " +
                    "cafe known for artist-collaboration menu items tied to current releases."
            )
            BodyText(
                "AKB48 Theater in Akihabara is the dedicated home theater of idol group AKB48, " +
                    "with near-daily live performances - the whole group was built around the " +
                    "idea of a theater-based idol act fans could see perform regularly. Nearby, " +
                    "the separate AKB48 Cafe & Shop serves idol-themed food and merchandise for " +
                    "fans who don't have theater tickets."
            )
            BodyText(
                "For most visitors, the everyday way to actually sing along to J-pop hits is a " +
                    "karaoke box - private-room karaoke chains like Big Echo and Karaoke Kan have " +
                    "locations all over the city, rented by the hour rather than performed on a " +
                    "stage."
            )

            SectionHeader("80s and retro museums or arcades")
            BodyText(
                "Super Potato in Akihabara is a multi-floor retro video game specialty store " +
                    "crammed with classic consoles, cartridges, and merchandise from the 80s and " +
                    "90s; its top floor is a small playable retro arcade, making it as much a " +
                    "nostalgia museum as a shop."
            )
            BodyText(
                "Mikado Arcade in Takadanobaba is a legendary classic-cabinet arcade, well known " +
                    "in the fighting-game community for keeping older titles alive on original " +
                    "hardware - it moved to a new nearby location in 2022 after its original " +
                    "building was slated for demolition, so it's worth confirming the current " +
                    "address before visiting."
            )
            BodyText(
                "GiGO Akihabara (the rebranded former Sega arcades, after Sega sold off its " +
                    "arcade business in 2022) still runs multiple floors of claw machines, " +
                    "rhythm games, and retro game corners across Akihabara - the neighborhood " +
                    "most associated with retro Japanese gaming culture."
            )
            BodyText(
                "HEY (Hirose Entertainment Yard) Taito, also in Akihabara, goes deeper still for " +
                    "shooting and fighting-game purists - a 6-floor arcade with 300+ playable " +
                    "cabinets, including a floor dedicated to \"bullet hell\" shoot-'em-ups and " +
                    "another stacked with classic 2D fighters, some of which never released " +
                    "outside Japan. Open daily from around 10:45am to 11pm, with games from ¥100 " +
                    "to ¥1,000."
            )
            BodyText(
                "Namja Town, inside Sunshine City in Ikebukuro, is a themed indoor food-and-games " +
                    "park built around a recreated Showa-era (mid-20th-century) Japanese " +
                    "shopping street, complete with a \"gyoza stadium\" of dumpling stalls and a " +
                    "dessert-themed amusement zone - more nostalgic-retro-Japan atmosphere than " +
                    "video games, but a fun complement to the arcade-focused stops above."
            )
            BodyText(
                "Odaiba Retro Museum, on the 4th floor of the Decks Tokyo Beach mall (a 2-minute " +
                    "walk from Odaiba-Kaihinkoen Station on the Yurikamome Line), reopened under " +
                    "this name in April 2025 after renovating the former \"Daiba 1-chome " +
                    "Shotengai\": a fully recreated 1960s Showa-era shopping street, right down to " +
                    "a sento bathhouse facade, mom-and-pop eateries, a police box, and vintage " +
                    "advertising boards, across 10 stores and 7 entertainment facilities. Adult " +
                    "admission is about ¥1,300 - it's more an immersive nostalgia streetscape than " +
                    "an arcade, but fits the retro-Japan theme here well."
            )
            Spacer(Modifier.height(16.dp))
        }
    }
}
