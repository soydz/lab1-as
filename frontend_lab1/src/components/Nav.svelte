<script>
    import { link, location } from "svelte-spa-router";

    const current = location;
</script>

<nav>
    <ul>
        <li class={$current == "/customer/create" || $current == "#/" ? "active" : ""}>
            <a href="#/customer/create" use:link class="contrast">Create</a>
        </li>
        <li class={$current == "/customer/see_all" ? "active" : ""}>
            <a href="#/customer/see_all" use:link class="contrast">See all</a>
        </li>

        <li class={$current == "/transaction/transfer" ? "active" : ""}>
            <a href="#/transaction/transfer" use:link class="contrast"
                >Transfer Found</a
            >
        </li>
        <li class={$current == "/transaction/history" ? "active" : ""}>
            <a href="#/transaction/history" use:link class="contrast">History</a
            >
        </li>
    </ul>
</nav>

<style>
    nav a {
        font-weight: 500;
        color: white;
    }

    li.active a {
        font-weight: bold;
        color: var( --pico-primary);
    }
    
</style>
