import { z } from 'zod'

export const schemaAddPessoa = z.object({
	nome: z.string()
		.nonempty('Nome da pessoa é obrigatório')
		.min(3, 'Nome da pessoa deve ter no mínimo 3 caracteres')
		.max(100, 'Nome da pessoa deve ter no máximo 100 caracteres')
})
