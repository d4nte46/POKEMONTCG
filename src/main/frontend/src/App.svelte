<script lang="ts">
    import {RouterContext, RouterView} from '@dvcol/svelte-simple-router/components';

    import type {Route, RouterOptions} from '@dvcol/svelte-simple-router/models';

    import HomeComponent from './lib/Home.svelte';
    import DemoComponent from "./lib/Demo.svelte";

    let currentRoute = null;


  const RouteName = {
    Home: 'home',
    Demo: 'demo',
  } as const;

  const theme = {
      demo : {
          background : "#000000"
      }
  }

  function getColor(string){
      return theme[string]?.background ;
  }

  type RouteNames = (typeof RouteName)[keyof typeof RouteName];

  export const routes: Readonly<Route<RouteNames>[]> = [
    {
      name: RouteName.Home,
      path: '/',
      component: HomeComponent,
    },
    {
      name: RouteName.Demo,
      path: `/${RouteName.Demo}`,
      component: DemoComponent,
    },
  ] as const;

  export const options: RouterOptions<RouteNames> = {
    routes,
  } as const;


</script>
<RouterContext {options} >
    <RouterView />
</RouterContext>