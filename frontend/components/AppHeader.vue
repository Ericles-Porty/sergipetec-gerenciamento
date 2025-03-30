<script setup lang="ts">
const menuItems = [
	{
		nome: 'Home',
		rota: '/',
	},
	{
		nome: 'Pessoas',
		rota: '/pessoas',
	},
	{
		nome: 'Projetos',
		rota: '/projetos',
	},
	{
		nome: 'Tarefas',
		rota: '/tarefas',
	},
	{
		nome: 'Configurações',
		rota: '/configuracoes',
	},
]
</script>

<template>
	<header class="w-screen p-10 flex flex-row justify-center items-center">
		<nav>
			<div class="wrapper">
				<ul class="flex flex-row gap-10 text-2xl">
					<li v-for="item in menuItems" :key="item.rota">
						<NuxtLink :to="item.rota" exact-active-class="bg-green-700 text-white" >
							{{ item.nome }}
						</NuxtLink>
					</li>
				</ul>
			</div>
		</nav>
	</header>
</template>


<style scoped>
[aria-current] {
	color: white;
}
</style>
