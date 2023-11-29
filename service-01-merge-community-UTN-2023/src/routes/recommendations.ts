import { Router } from "express";
import { recommendedCommunityMerge } from "../service/recommendedCommunityMerge";
import { Community } from "../types/community";

const router = Router();

router.get("/", (_req, res) => {
    res.send("RECOMENDACIONES");
  });


router.post("/", (req, res) => {
 
const communities: Community[] = req.body;

const possibleMerges: { community1: Community, community2: Community }[] = recommendedCommunityMerge(communities);
  
    // Envía las posibles fusiones
    res.json({ possibleMerges  });
  
    
  });

/**
 * Post Track
 * @openapi
 * /api/recommendations:
 *   post:
 *     tags:
 *     - Recomendaciones
 *     summary: Recomendar comunidades fusionables.
 *     description: Recomendar una lista de comunidades fusionables.
 *     requestBody:
 *       description: Lista de comunidades a evaluar.
 *       required: true
 *       content:
 *         application/json:
 *           schema:
 *             type: array
 *             items:
 *               $ref: '#/components/schemas/Community' # Referencia al tipo Community
 *     responses:
 *       200:
 *         description: Lista de recomendaciones.
 *         content:
 *           application/json:
 *             schema:
 *               type: array
 *               items:
 *                 $ref: '#/components/schemas/PossibleMerge' # Referencia al tipo PosibleMerge
 *       400:
 *         description: Solicitud incorrecta o incompleta.
 *       500:
 *         description: Error interno del servidor.
 */

export default router;