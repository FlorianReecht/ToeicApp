<script lang="ts">
	import { page } from '$app/stores';
	import { onMount } from "svelte";

	// Show mobile icon and display menu
	let showMobileMenu = false;

// List of navigation items
const navItems = [
  { label: "Leçons", href: "./lessonsMenu" },
  { label: "Questions", href: "./questionsMenu" },
  { label: "Inscription TOEIC", href: "/inscription" },
  { label: "Forum", href: "/forum" },
  { label: "Compte", href: "/profil" },
  { label: "Connexion", href: "/login"}
];

if (typeof window !== "undefined"){
  if (localStorage.getItem('isLoggedIn') === 'true'){
        navItems.splice(5, 1);
        }
      }
      
if (typeof window !== "undefined"){
  if (localStorage.getItem('isLoggedIn') !== 'true'){
        navItems.splice(1, 1);
        navItems.splice(2, 1);
        navItems.splice(2, 1);
        }
      }
// Mobile menu click event handler
const handleMobileIconClick = () => (showMobileMenu = !showMobileMenu);

// Media match query handler
const mediaQueryHandler = e => {
  // Reset mobile state
  if (!e.matches) {
	showMobileMenu = false;
  }
};

// Attach media query listener on mount hook
onMount(() => {
  const mediaListener = window.matchMedia("(max-width: 767px)");

  mediaListener.addListener(mediaQueryHandler);
});
  
  let isDropdownOpen = false // default state (dropdown close)

const handleDropdownClick = () => {
  isDropdownOpen = !isDropdownOpen // togle state on click
}

const handleDropdownFocusLoss = ({ relatedTarget, currentTarget }) => {
  // use "focusout" event to ensure that we can close the dropdown when clicking outside or when we leave the dropdown with the "Tab" button
  if (relatedTarget instanceof HTMLElement && currentTarget.contains(relatedTarget)) return // check if the new focus target doesn't present in the dropdown tree (exclude ul\li padding area because relatedTarget, in this case, will be null) 
  isDropdownOpen = false
}
</script>

<svelte:head>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css">
</svelte:head>

<header>
	<nav>
		<div class="inner">
		  <!-- svelte-ignore a11y-click-events-have-key-events -->
		  <div on:click={handleMobileIconClick} class={`mobile-icon${showMobileMenu ? ' active' : ''}`}>
			  <div class="middle-line"></div>
		  </div>
		  <ul class={`navbar-list${showMobileMenu ? ' mobile' : ''}`}>
			  {#each navItems as item}
				    <li>
						  <a href={item.href}>{item.label}</a>
				    </li>
			  {/each}
		  </ul>
		</div>
	</nav>
</header>

<style>
	nav {
    background-color: rgba(0, 0, 0, 0.8);
    font-family: "Helvetica Neue", "Helvetica", "Arial", sans-serif;
    height: 45px;
  }

  .inner {
    max-width: 980px;
    padding-left: 20px;
    padding-right: 20px;
    margin: auto;
    box-sizing: border-box;
    align-items: center;
    height: 100%;
  }

  .mobile-icon {
    width: 25px;
    height: 14px;
    position: relative;
    cursor: pointer;
  }

  .mobile-icon:after,
  .mobile-icon:before,
  .middle-line {
    content: "";
    position: absolute;
    width: 100%;
    height: 2px;
    background-color: #fff;
    transition: all 0.4s;
    transform-origin: center;
  }

  .mobile-icon:before,
  .middle-line {
    top: 0;
  }

  .mobile-icon:after,
  .middle-line {
    bottom: 0;
  }

  .mobile-icon:before {
    width: 66%;
  }

  .mobile-icon:after {
    width: 33%;
  }

  .middle-line {
    margin: auto;
  }

  .mobile-icon:hover:before,
  .mobile-icon:hover:after,
  .mobile-icon.active:before,
  .mobile-icon.active:after,
  .mobile-icon.active .middle-line {
    width: 100%;
  }

  .mobile-icon.active:before,
  .mobile-icon.active:after {
    top: 50%;
    transform: rotate(-45deg);
  }

  .mobile-icon.active .middle-line {
    transform: rotate(45deg);
  }

  .navbar-list {
    display: none;
    width: 100%;
    justify-content: space-between;
    margin: 0;
    padding: 0 40px;
  }

  .navbar-list.mobile {
    background-color: rgba(0, 0, 0, 0.8);
    position: fixed;
    display: block;
    height: calc(100% - 45px);
    bottom: 0;
    left: 0;
  }

  .navbar-list li {
    list-style-type: none;
    position: relative;
  }

  .navbar-list li:before {
    content: "";
    position: absolute;
    bottom: 0;
    left: 0;
    width: 100%;
    height: 1px;
    background-color: #424245;
  }

  .navbar-list a {
    color: #f2f2f2;
    text-decoration: none;
	  display: flex;
	  height: 45px;
	  align-items: center;
    padding: 0px 10px;
    font-size: 17px;
  }
	
	.apprendrebtn{
		border: none;
		color: #f2f2f2;;
    height: 45px;
    align-items: center;
    padding: 10px 10px;
    text-decoration: none;
    font-size: 17px;
	}

  .apprendrebtn.mobile{
		border: none;
		color: #f2f2f2;
    padding: 10px 10px;
    align-items: center;
    text-decoration: none;
    font-size: 17px;
    height: 45px;
	}

  .apprendrebtnsub{
    color: #f2f2f2 ;
    background-color: #424245;
    font-size: 17px;
    text-decoration: none;
  }

  @media only screen and (min-width: 767px) {
    .mobile-icon {
      display: none;
    }

    .navbar-list {
      display: flex;
      padding: 0;
    }

    .navbar-list a {
      display: inline-flex;
    }

    .dropdown{
      display: grid;
    }
  }
</style>
