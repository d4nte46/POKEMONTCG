<script>
    import { crossfade } from 'svelte/transition';
    import { sineInOut } from 'svelte/easing';

    import  MegaGardevoir  from "../assets/MegaGardevoir.png"
    const bodyClass = "home-comp"

    $effect(()=> {
        document.body.classList.add(bodyClass);
        return () => {
            document.body.classList.remove(bodyClass);
        }

    });

    let isLogin = $state(true);
    let email = $state('');
    let password = $state('');
    let confirmPassword = $state('');

    const [send, receive] = crossfade({
        duration: 300,
        easing: sineInOut
    });

    function logininfo(a,b){
        console.log(a);
        console.log(b);
    }

    function signup(a,b,c){
        console.log(a);
        console.log(b);
        console.log(c);
    }



</script>

<div class="grid grid-cols-6 rounded-xl outline-1 outline-black min-w-[80vw] min-h-[80vh] max-w-[80vw] max-h-[80vh]bg-color-transparent">
    <!-- Left column (3 cols wide) -->
    <div class="row-span-3 grid grid-cols-3 col-span-3 rounded-l-xl rounded-r-none bg-[#696969] text-[#FFFFFF]">
        <!-- Logo section -->
        <div class="row-span-2 col-span-1 text-5xl tracking-wider text-justify flex flex-cols items-center pt-[7vh]">
            POKE<br>
            PORT<br>
            FOLIO
        </div>
        <!-- Form section -->
        <div class="row-span-2 col-span-2 pt-[5px] ">
            <div class="flex-row text-3xl justify-between px-[3vw] ">
                <button class="{isLogin === true ? 'underline' : ''} pr-[2vw]" on:click={()=>{isLogin = true}}>
                    LOGIN
                </button>
                <button class="{isLogin === false ? 'underline' : ''}" on:click={()=>{isLogin = false}}>
                    SIGNUP
                </button>
            </div>
            <div class="absolute min-w-[25%] min-h-[25%]">
                {#if isLogin }

                    <div
                            class = "flex flex-col min-w-fit py-[20vh] justify-evenly text-xl"
                            in:receive={{key: 'form'}} out:send={{key: 'form'}}
                    >
                        <input
                                type="email"
                                placeholder="EMAIL"
                                bind:value={email}
                                class="border-b-3 my-3 focus:outline-none "
                        />
                        <input
                                type="password"
                                placeholder="PASSWORD"
                                bind:value={password}
                                class="border-b-3 my-3 "
                        />
                        <a class="mt-[7vh] hover:underline">FORGOT PASSWORD?</a>
                    </div>
                {:else}
                    <div
                            class = "flex flex-col py-[20vh] justify-evenly text-xl "
                            in:receive={{key: 'form'}} out:send={{key: 'form'}}
                    >
                        <input
                                type="email"
                                placeholder="EMAIL"
                                bind:value={email}
                                class="border-b-3 my-3"
                        />
                        <input
                                type="password"
                                placeholder="PASSWORD"
                                bind:value={password}
                                class="border-b-3 my-3"
                        />
                        <input
                                type="password"
                                placeholder="CONFIRM PASSWORD"
                                bind:value={confirmPassword}
                                class="border-b-3 my-3"
                        />
                    </div>
                {/if}
            </div>
        </div>

        <!-- Button section -->
        <div class="row-span-1 col-span-3 content-center">
            <button class="bg-[#F4F5F7] hover:shadow-2xl text-black text-2xl rounded min-h-[7vh] min-w-[35vw] " on:click={()=> logininfo(email,password)}>
                {isLogin === true
                    ? "LOGIN"
                    : "SIGNUP"
                }
            </button>
        </div>
    </div>

    <!-- Right column (3 cols wide) -->
    <div class="row-span-3 col-span-3 flex flex-col justify-end ">
        <img src={MegaGardevoir} alt="Cool Gardevoir Pic :D">
    </div>
</div>

<style>
    :global(body.home-comp){
        background-color: #F4F5F7 ;
    }
</style>

