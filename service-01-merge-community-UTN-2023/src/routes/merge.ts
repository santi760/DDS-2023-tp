import { Router } from "express";
import { mergeCommunities } from "../service/mergeCommunities";

const router = Router();

router.get("/", (_req, res) => {
    res.send("FUSION");
  });
  

  router.post("/", (req, res) => {
 
    const { community1, community2 } = req.body;
  
    if (!community1 || !community2) {
      return res
        .status(400)
        .json({ error: "Se requieren dos comunidades válidas para la fusión." });
    }
    // Fusiona las dos comunidades
    const mergedCommunity = mergeCommunities(community1, community2);
  
    // Envía la comunidad fusionada como respuesta
    res.json({ mergedCommunity });
  });

  /**
 * Post Track
 * @openapi
 * /api/fusion:
 *   post:
 *     summary: Fusionar dos comunidades.
 *     description: Fusiona dos comunidades proporcionadas en el cuerpo de la solicitud.
 *     tags: [Fusion]
 *     parameters:
 *       - name: community1
 *         in: body
 *         description: Primera comunidad a fusionar.
 *         required: true
 *         schema:
 *           type: object
 *           properties:
 *             id:
 *               type: string
 *             name:
 *               type: string
 *             degreeOfConfidence:
 *               type: number
 *             members:
 *               type: array
 *               items:
 *                 type: object
 *             interestingServices:
 *               type: array
 *               items:
 *                 type: object
 *             interestingEstablishments:
 *               type: array
 *               items:
 *                 type: object
 *       - name: community2
 *         in: body
 *         description: Segunda comunidad a fusionar.
 *         required: true
 *         schema:
 *           type: object
 *           properties:
 *             id:
 *               type: string
 *             name:
 *               type: string
 *             degreeOfConfidence:
 *               type: number
 *             members:
 *               type: array
 *               items:
 *                 type: object
 *             interestingServices:
 *               type: array
 *               items:
 *                 type: object
 *             interestingEstablishments:
 *               type: array
 *               items:
 *                 type: object
 *     responses:
 *       200:
 *         description: Comunidad fusionada exitosamente.
 *         content:
 *           application/json:
 *             schema:
 *               type: array
 *               items:
 *                 $ref: '#/components/schemas/MergeCommunities'
 *       400:
 *         description: Error de solicitud, se requieren dos comunidades válidas para la fusión.
 *         schema:
 *           type: object
 *           properties:
 *             error:
 *               type: string
 */

export default router;