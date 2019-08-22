# Fridges
Create fridges for storing stuff!
You can put stuff into fridges and you can repair tools with anvils!

**Fridges** - Fridges you can put stuff into and anvils you can repair tools with!  

**Version:** v0.3  
This plugin adds fridges that functions like chests and anvils that can repair tools  

<span style="font-size: 15px"><span style="color: rgb(255, 0, 0)">**If you are upgrading to 0.3 make sure you delete your old config.**</span></span>  

**Dependencies:**  

*   [Vault](https://dev.bukkit.org/projects/vault)

**Features:**  

*   Fridges that functions like chests.
*   Anvils that can repair your tools for money
*   Vault
*   Simple protections

**Screenshot of an example usage:**  
![[​IMG]](http://i.imgur.com/cfjwO.jpg)  

**Video of how to build and use:**  
<object width="500" height="300" data="https://www.youtube.com/v/ENv1TMDgY6M&amp;fs=1" type="application/x-shockwave-flash"><param name="movie" value="https://www.youtube.com/v/ENv1TMDgY6M&amp;fs=1"> <param name="allowFullScreen" value="true"> <param name="wmode" value="opaque"> <embed src="https://www.youtube.com/v/ENv1TMDgY6M&amp;fs=1" type="application/x-shockwave-flash" allowfullscreen="true" wmode="opaque" width="500" height="300"></object>  

**Showcase from my awesome mate:**  
<object width="500" height="300" data="https://www.youtube.com/v/KP-prBiC-XM&amp;fs=1" type="application/x-shockwave-flash"><param name="movie" value="https://www.youtube.com/v/KP-prBiC-XM&amp;fs=1"> <param name="allowFullScreen" value="true"> <param name="wmode" value="opaque"> <embed src="https://www.youtube.com/v/KP-prBiC-XM&amp;fs=1" type="application/x-shockwave-flash" allowfullscreen="true" wmode="opaque" width="500" height="300"></object>  

**How to use:**  
_Fridges:_  

First you place a chest, then place an iron block ontop and another iron block ontop of that.  
Then you can rightclick the top ironblock to acces the chest at the bottom.  

_Anvils:_  

Place an iron block and place a stone pressureplate under it.  
Leftclick on the iron block with a used tool in your hand to display the repair cost.  
Rightclick on the iron block with a used tool to repair it.  

**Config file:**  
Base cost is the cost which would be charged if the tool was used completely.  

<div class="bbCodeBlock bbCodeCode">

<div class="type">Code:</div>

<pre>anvils:
    enabled: true - if anvils can be used
    price:
        wood: 50 - base price for wood repair
        diamond: 500 - base price for diamond
        stone: 75 - price for stone
        gold: 250 - price for gold
        iron: 150 - price for iron
fridges:
    enabled: true - if fridges can be used
debug: false - if you want extra output to the console</pre>

</div>

**Commands:**  

<div class="bbCodeBlock bbCodeCode">

<div class="type">Code:</div>

<pre>/fridges reload - reload the config file</pre>

</div>

**Permissions nodes:**  

<div class="bbCodeBlock bbCodeCode">

<div class="type">Code:</div>

<pre>fridges.reload - for /fridges reload
fridges.fridge.use - be able to open fridges
fridges.fridge.break - you need this to break fridges
fridges.anvil.repair - for when you repair your tool
fridges.anvil.info - to get the cost of the item you are holding
fridges.anvil.break - you need this to break anvils</pre>

</div>

<span style="color: rgb(255, 0, 0)">**Posting bugs and errors:**</span>  
Be sure to include:  

*   Your version of Bukkit
*   Your version of Fridges
*   Any other plugins that may cause the issue
*   The error from the console (if any)

<span style="color: rgb(255, 0, 0)">**If you have any suggestion to new stuff i can add or things that needs improvement then feel free to post them below**</span>  

**TODO:**  

*   <span style="text-decoration: line-through">Fridge protection so you cant just go around and destroy fridges</span>
*   Locks/passwords

**[Changelog](http://en.wikipedia.org/wiki/Changelog):**  
Version 0.3  

*   Added simple protection
*   Completely recoded the plugin because Eclipse failed on me
*   Small bugfixes

Version 0.2  

*   Added anvils

Version 0.1  

*   First release
